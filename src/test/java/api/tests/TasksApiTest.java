package api.tests;

import api.client.AuthClient;
import api.client.TasksClient;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TasksApiTest {

    @Test(groups = "api")
    public void shouldCreateTaskAndThenSeeItInList() {
        String baseUrl = "http://localhost:8055";
        String email = "admin@example.com";
        String password = "admin123";

        String token = new AuthClient(baseUrl, email, password).loginAndGetAccessToken();

        TasksClient tasksClient = new TasksClient(baseUrl);

        // 1) Create a task
        var createResponse = tasksClient.createTaskRaw(token, "make some fun", "todo");
        Assert.assertEquals(createResponse.statusCode(), 200, "Create task should return 200");

        // 2) Read tasks
        var listResponse = tasksClient.getTasksRaw(token);
        Assert.assertEquals(listResponse.statusCode(), 200, "Get tasks should return 200");

        String body = listResponse.asString();
        Assert.assertTrue(body.contains("make some fun"), "Tasks list should contain created task title");
    }
}