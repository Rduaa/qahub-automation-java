package api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TasksClient {

    private final String baseUrl;

    // If your UI works with "/server/tasks", keep it as is.
    private static final String TASKS_PATH = "/server/tasks";

    public TasksClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Creates a task via backend API and returns the raw response.
     */
    public Response createTaskRaw(String accessToken, String title, String status) {
        String body = String.format("{\"title\":\"%s\",\"status\":\"%s\"}", escapeJson(title), escapeJson(status));

        return given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(body)
                .when()
                .post(TASKS_PATH)
                .then()
                .extract()
                .response();
    }

    /**
     * Gets tasks list via backend API and returns the raw response.
     */
    public Response getTasksRaw(String accessToken) {
        return given()
                .baseUri(baseUrl)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(TASKS_PATH)
                .then()
                .extract()
                .response();
    }

    private String escapeJson(String value) {
        return value == null ? "" : value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
