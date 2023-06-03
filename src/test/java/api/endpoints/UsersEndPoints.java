package api.endpoints;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class UsersEndPoints {

    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");// load the properties file
    return routes;
    }

    public static Response createUser(User payload){
        String postURL = getURL().getString("post_URL");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(postURL);
        return response;
    }

    public static Response getUser(String username){
        Response response = given().pathParams("username",username)
                .contentType(ContentType.JSON)
                .when().get(getURL().getString("get_URL"));
        return response;
    }

    public static Response deleteUser(String username){
        String delete_URL = getURL().getString("delete_URL");
        Response response = given().pathParams("username", username)
                .when().delete(delete_URL);
        return response;
    }

    public static Response updateUser(String userName, User payload) {
        String update_url = getURL().getString("update_URL");
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("username", userName)
                .body(payload)
                .when()
                .put(update_url);
        return response;
    }
}
