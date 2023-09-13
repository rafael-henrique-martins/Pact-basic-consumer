package br.ce.wcaquino.consumer.tasks.service.pact;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import br.ce.wcaquino.consumer.tasks.model.Task;
import br.ce.wcaquino.consumer.tasks.service.TasksConsumer;
import org.junit.Rule;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TasksConsumerContractTest {

    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("Tasks", this);
//    public PactProviderRule mockProvider = new PactProviderRule("Tasks", "localhost",8080,this); alternativa

    @Pact(consumer = "BasicConsumer")
    public RequestResponsePact createPact(PactDslWithProvider builder){ // essa classe é a minha espectativa
        DslPart body = new PactDslJsonBody()
                .numberType("id", 1L)
                .stringType("task", "Task from pact")
                .stringType("dueDate");

        return builder
            .given("There is a task with id = 1")// Informar o que preciso, ex: quando monto uma classe com os campos para retornar no when
            .uponReceiving("Retrieve Task #1") // a acao que eu quero
                .path("/todo/1")
                .method("GET")// com o metodo
            .willRespondWith()// quando eu tiver os campos acima, o que eu quero que ele me responda
                .status(200)// o que ele vai responder
                .body(body)
//                .body("{\"id\": 1,\"task\": \"Task from pact\",\"dueDate\": \"2000-22-11\"}") alternativa
            .toPact();
    }

    @Test
    @PactVerification
    public void test() throws IOException {
        TasksConsumer consumer = new TasksConsumer(mockProvider.getUrl()); // vai montar a url pra mim em tempo de execução, ou posso fazer do jeito que esta comentado linha 24
        System.out.println(mockProvider.getUrl());

        Task task = consumer.getTask(1L);
        assertThat(task.getId(), is(1L));
        assertThat(task.getTask(), is("Task from pact"));

    }
}
