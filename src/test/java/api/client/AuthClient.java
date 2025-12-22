package api.client;

import api.model.LoginResponse;
import core.config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class AuthClient {

    public AuthClient() {
        RestAssured.baseURI = ConfigReader.get("api.baseUrl");
    }

    public String loginAndGetAccessToken() {
        String email = ConfigReader.get("user.email");
        String password = ConfigReader.get("user.password");

        LoginResponse resp =
                given()
                        .contentType(ContentType.JSON)
                        .body("{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}")
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(LoginResponse.class);

        if (resp == null || resp.data == null || resp.data.access_token == null || resp.data.access_token.isBlank()) {
            throw new AssertionError("access_token is empty");
        }
        return resp.data.access_token;
    }
}
