package api.tests;

import api.client.AuthClient;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginApiTest {

    @Test(groups = "api")
    public void shouldLoginAndReturnAccessToken() {
        String token = new AuthClient().loginAndGetAccessToken();
        Assert.assertTrue(token.length() > 20, "Token looks too short");
    }
}
