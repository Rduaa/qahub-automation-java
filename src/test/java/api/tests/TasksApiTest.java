package api.tests;

import api.client.AuthClient;
import api.client.TasksClient;
import api.model.TaskDto;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TasksApiTest {

    private static final String BASE_URL = "http://localhost:8055";
    private static final String EMAIL = "admin@example.com";
    private static final String PASSWORD = "Admin12345!";

    @Test(groups = "api")
    public void shouldCreateAndFindTaskInList() {
        String token = new AuthClient(BASE_URL, EMAIL, PASSWORD).loginAndGetAccessToken();

        TasksClient tasksClient = new TasksClient(BASE_URL);

        String title = "task-" + System.currentTimeMillis();
        TaskDto created = tasksClient.createTask(token, title, "todo");

        Assert.assertNotNull(created, "Created task should not be null");
        Assert.assertNotNull(created.id, "Created task id should not be null");
        Assert.assertEquals(created.title, title, "Created task title mismatch");
        Assert.assertEquals(created.status, "todo", "Created task status mismatch");

        List<TaskDto> tasks = tasksClient.getTasks(token);

        boolean found = tasks.stream().anyMatch(t ->
                t.id != null && t.id.equals(created.id) &&
                        title.equals(t.title) &&
                        "todo".equals(t.status)
        );

        Assert.assertTrue(found, "Created task was not found in tasks list");
    }
}
