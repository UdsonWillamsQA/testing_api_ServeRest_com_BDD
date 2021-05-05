package suport.api;

import io.restassured.response.Response;
import suport.domain.User;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class UsuarioApi {

    private final String USER_ENDPOINT = "/usuarios";

    public Response cadastrarUsuario(User user) {
        return given().
                body(user).
            when().
                post(USER_ENDPOINT);
    }

    public Response getAllUsers(){
        return when().
                get(USER_ENDPOINT);
    }

    public Response getById(String idUsario) {
        return when().
                get(USER_ENDPOINT + "/" + idUsario);
    }

    public Response deletarPorId(String idUsario) {
        return when().
                delete(USER_ENDPOINT + "/" + idUsario);
    }

    public Response alterarUsuario(String idUsario) {
        var user = User.builder().email("udson@gmailAtualizado.com").password("teste2").build();
        return given().
                body(user).
            when().put(USER_ENDPOINT + "/" + idUsario);
    }

    public String getByName(String nomeUsuario) {
        String id = when().get(USER_ENDPOINT + "?nome=" + nomeUsuario).then().extract().body().jsonPath().getString("usuarios._id");
        id = id.replaceAll("\\[", "");
        id = id.replaceAll("\\]", "");
        System.out.println(id);
        return id;
    }
}

