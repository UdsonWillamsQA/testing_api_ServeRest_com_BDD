package Steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import static Steps.UserStepdefinitions.idUsario;
import static io.restassured.RestAssured.when;

public class Configs {

    @Before
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = ("http://localhost:3000");
        //A especificação do request
        RestAssured.requestSpecification = new RequestSpecBuilder()/*addHeader("Authorization", token)*/.setContentType(ContentType.JSON).build();
    }
    @After("@excluirUsuario")
    public void tearDown(){
        when().
            delete("/usuarios" + "/" + idUsario).then().statusCode(HttpStatus.SC_OK);
        System.out.println(idUsario);
    }
}