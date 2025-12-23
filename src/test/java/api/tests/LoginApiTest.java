package api.tests;

import api.client.AuthClient;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginApiTest {

    private static final String BASE_URL = "http://localhost:8055";
    private static final String EMAIL = "admin@example.com";
    private static final String PASSWORD = "Admin12345!";

    @Test(groups = "api")
    public void shouldLoginAndReturnAccessToken() {
        String token = new AuthClient(BASE_URL, EMAIL, PASSWORD).loginAndGetAccessToken();
        Assert.assertTrue(token.length() > 20, "Token looks too short");
    }
}
