package adapters;

import com.google.gson.Gson;
import io.restassured.response.ValidatableResponse;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public class BaseAdapter {

    public static final String token = System.getenv().getOrDefault("token", PropertyReader.getProperty("token"));
    public static final String URLAPI = System.getenv().getOrDefault("urlApi", PropertyReader.getProperty("urlApi"));

    Gson converter = new Gson();

    public ValidatableResponse get(String uri) {
        return
                given()
                        .header("Token", token)
                        .header("Content-Type", "application/json")
                        .when()
                        .get(URLAPI + uri)
                        .then()
                        .log().all();

    }

    public ValidatableResponse post(String uri, String body) {
        return
                given()
                        .header("Token", token)
                        .header("Content-Type", "application/json")
                        .body(body)
                        .log().all()
                        .when()
                        .post(URLAPI + uri)
                        .then()
                        .log().all();
    }

    public ValidatableResponse patch(String uri, String body) {
        return
                given()
                        .header("Token", token)
                        .header("Content-Type", "application/json")
                        .body(body)
                        .log().all()
                        .when()
                        .patch(URLAPI + uri)
                        .then()
                        .log().all();
    }

    public ValidatableResponse delete(String uri) {
        return
                given()
                        .header("Token", token)
                        .header("Content-Type", "application/json")
                        .log().all()
                        .when()
                        .delete(URLAPI + uri)
                        .then()
                        .log().all();
    }

}
