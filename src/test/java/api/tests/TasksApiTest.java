package api.tests;

import api.client.AuthClient;
import api.client.TasksClient;
import config.ConfigReader;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TasksApiTest {

    @Test(groups = "api")
    public void shouldUpdateSingletonTaskAndReadItBack() {
        String baseUrl = ConfigReader.get("base.url");         // must be http://localhost:8055
        String email = ConfigReader.get("admin.email");
        String password = ConfigReader.get("admin.password");

        String token = AuthClient.loginAndGetAccessToken(baseUrl, email, password);

        Response updateResp = TasksClient.upsertSingletonTask(baseUrl, token, "Test task");
        Assert.assertEquals(updateResp.statusCode(), 200, "Update failed: " + updateResp.asString());

        Response getResp = TasksClient.getSingletonTask(baseUrl, token);
        Assert.assertEquals(getResp.statusCode(), 200, "Get failed: " + getResp.asString());
    }
}