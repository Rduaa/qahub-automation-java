package api.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TasksClient {

    private static String itemsTasksUrl(String baseUrl) {
        // Guarantees no double slashes
        return baseUrl.replaceAll("/+$", "") + "/items/tasks";
    }

    public static Response upsertSingletonTask(String baseUrl, String token, String title) {
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .basePath("") // IMPORTANT: prevents accidental "/api"
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{\"title\":\"" + title + "\"}")
                .when()
                .patch("/items/tasks"); // singleton => PATCH, not POST
    }

    public static Response getSingletonTask(String baseUrl, String token) {
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .basePath("") // IMPORTANT
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/items/tasks");
    }
}
