package api.tests;

import api.client.AuthClient;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginApiTest {

    @Test(groups = "api")
    public void shouldLoginAndReturnAccessToken() {
        String baseUrl = ConfigReader.get("base.url");
        String email = ConfigReader.get("admin.email");
        String password = ConfigReader.get("admin.password");

        String token = new AuthClient(baseUrl, email, password).loginAndGetAccessToken();
        Assert.assertTrue(token.length() > 20, "Token looks too short");
    }
}
