package suport.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginApi {

    private final String LOGIN_ENDPOINT = "/login";

    public Response fazLoginString(String emailErrado){
        return given().
                body("{\"email\": \""+emailErrado+"\", \"password\": \"teste\"}").
            when().
                post(LOGIN_ENDPOINT);
    }
    public Response fazLoginResponse(){
        return given().
                body("{\"email\": \"udson@gmail.com\", \"password\": \"passtest\"}").
            when().
                post(LOGIN_ENDPOINT);
    }
}
