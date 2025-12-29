package api.client;

import api.model.LoginResponse;
import io.restassured.RestAssured;

import static io.restassured.http.ContentType.JSON;

public class AuthClient {

    public static String loginAndGetAccessToken(String baseUrl, String email, String password) {
        LoginResponse resp =
                RestAssured.given()
                        .baseUri(baseUrl)
                        .contentType(JSON)
                        .body("{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}")
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(LoginResponse.class);

        return resp.getData().getAccessToken();
    }
}