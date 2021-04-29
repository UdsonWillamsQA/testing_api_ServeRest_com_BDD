package stepDefinitions;

import io.cucumber.java.pt.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import suport.domain.Login;
import suport.api.LoginApi;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginSteps {

    Login login;
    LoginApi loginApi;
    String resposta;
    Response response;
    public static String token;

    public LoginSteps(){
        loginApi = new LoginApi();
        login = Login.builder().build();
    }

    @Quando("é fornecido um login correto")
    public void queEuForneçoUmLoginCorreto() {
        response = loginApi.fazLoginResponse();
        token = response.jsonPath().getString("authorization");
    }

    @Então("deve retornar status code 200 OK")
    public void retornaStatusCodeOK() {
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @E("devo receber uma mensagem de sucesso")
    public void devoReceboUmaMensagemDeSucesso() {
        resposta = response.then().extract().body().asString();
        assertThat(true, is(resposta.contains("Login realizado com sucesso")));
    }

    @Quando("é fornecido um login incorreto")
    public void queEuFornecerUmLoginIncorreto() {
        response = loginApi.fazLoginString("elida@gmail.com");
    }

    @Entao("devo receber uma mensagem de erro na resposta")
    public void devoReceberUmaMensagemDeErroNaResposta() {
        resposta = response.then().extract().body().asString();
        assertThat(true, is(resposta.contains("Email e/ou senha inválidos")));
    }

    @E("o sistema deve retornar status code 401 UNAUTHORIZED")
    public void retornaStatusCodeUNAUTHORIZED() {
        response.then().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Dado("^que o usuario estiver cadastrado$")
    public void queOUsuarioEstiverCadastrado() {
    }
}
