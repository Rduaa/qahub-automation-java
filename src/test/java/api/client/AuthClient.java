package api.client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthClient {

    private final String email;
    private final String password;

    public AuthClient(String baseUrl, String email, String password) {
        RestAssured.baseURI = baseUrl;
        this.email = email;
        this.password = password;
    }

    public String loginAndGetAccessToken() {
        Map<String, Object> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        String token =
                given()
                        .contentType(ContentType.JSON)
                        .body(body)
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getString("data.access_token");

        if (token == null || token.isBlank()) {
            throw new AssertionError("access_token is empty");
        }

        return token;
    }
}
