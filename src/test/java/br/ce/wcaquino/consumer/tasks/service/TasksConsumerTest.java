package br.ce.wcaquino.consumer.tasks.service;

import br.ce.wcaquino.consumer.tasks.model.Task;
import br.ce.wcaquino.consumer.utils.RequestHelper;
import junit.framework.TestCase;
import org.apache.http.client.ClientProtocolException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TasksConsumerTest {
    private static final String URL_INVALIDA = "localhost:8080";
    @InjectMocks
    private TasksConsumer consumer = new TasksConsumer(URL_INVALIDA);
    @Mock
    private RequestHelper helper;
    @Test
    public void shouldGetAnExistingTask() throws IOException {

        Map<String, String> expectTask = new HashMap<>();
        expectTask.put("id", "1");
        expectTask.put("task", "Task from pact");
        expectTask.put("dueDate", "2000-01-01");

//        System.out.println(expectTask);
//
//        Mockito.when(helper.get(URL_INVALIDA + "/todo/1")).thenReturn(expectTask);
//        Task task = consumer.getTask(1L);

//        Assert.assertNull(task);
//        Assert.assertThat(task.getId(), CoreMatchers.is("1"));

    }
}