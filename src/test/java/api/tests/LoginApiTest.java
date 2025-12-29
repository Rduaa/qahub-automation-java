package api.tests;

import api.client.AuthClient;
import config.ConfigReader;
import org.testng.annotations.Test;

public class LoginApiTest {

    @Test(groups = "api")
    public void shouldLoginSuccessfully() {
        String baseUrl = ConfigReader.get("base.url");
        String email = ConfigReader.get("admin.email");
        String password = ConfigReader.get("admin.password");

        String token = AuthClient.loginAndGetAccessToken(baseUrl, email, password);
        System.out.println("TOKEN = " + token);
    }
}