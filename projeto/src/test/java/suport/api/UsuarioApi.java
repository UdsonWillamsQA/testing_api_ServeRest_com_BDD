package suport.api;

import io.restassured.response.Response;
import resources.domain.User;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class UsuarioApi {

    private final String USER_ENDPOINT = "/usuarios";
    private Response retorno;
    private String id;

    public Response cadastrarUsuario(User user) {
        return given().
                body(user).
            when().
                post(USER_ENDPOINT);
    }
    public Response procuraUsuarios(){
        return when().
                get(USER_ENDPOINT);
    }

    public Response getPorId(String idUsario) {
        return when().
                get(USER_ENDPOINT + "/" + idUsario);
    }

    public Response deletaPorId(String idUsario) {
        return when().
                delete(USER_ENDPOINT + "/" + idUsario);
    }

    public Response alteraUsuario(String idUsario) {
        var user = User.builder().email("udson@gmailAtualizado.com").password("teste2").build();
        return given().
                body(user).
            when().put(USER_ENDPOINT + "/" + idUsario);
    }
}

