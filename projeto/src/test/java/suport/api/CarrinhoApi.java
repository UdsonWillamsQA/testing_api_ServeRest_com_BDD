package suport.api;

import stepDefinitions.CarrinhoSteps;
import stepDefinitions.LoginSteps;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class CarrinhoApi {

    private String CARRINHO_ENDPOINT = "/carrinhos";

    public Response cadastrarCarrinho(Map carrinho) {
        return given().
                header("Authorization", LoginSteps.token).
                body(carrinho).
            when().
                post(CARRINHO_ENDPOINT);
    }

    public Response pegaCarrinhos() {
        return when().
                get(CARRINHO_ENDPOINT);
    }

    public Response pegarCarrinho() {
        return when().
                get(CARRINHO_ENDPOINT + "/" + CarrinhoSteps.id_carrinho);
    }

    public Response concluirCompra() {
        return given().
                header("Authorization", LoginSteps.token).
            when().
                delete(CARRINHO_ENDPOINT + "/concluir-compra");
    }

    public Response cancelarCompra() {
        return given().
                header("Authorization", LoginSteps.token).
            when().
                delete(CARRINHO_ENDPOINT + "/cancelar-compra");
    }
}
