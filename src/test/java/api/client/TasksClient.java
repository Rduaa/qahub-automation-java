package api.client;

import api.model.TaskDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TasksClient {

    public TasksClient(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

    public TaskDto createTask(String accessToken, String title, String status) {
        Map<String, Object> body = new HashMap<>();
        body.put("title", title);
        body.put("status", status);

        return given()
                .contentType(ContentType.JSON)
                .auth().oauth2(accessToken)
                .body(body)
                .when()
                .post("/items/tasks")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getObject("data", TaskDto.class);
    }

    public List<TaskDto> getTasks(String accessToken) {
        return given()
                .auth().oauth2(accessToken)
                .when()
                .get("/items/tasks?sort=-id&limit=50")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("data", TaskDto.class);
    }
}
