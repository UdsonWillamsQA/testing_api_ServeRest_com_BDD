package suport.api;

import stepDefinitions.LoginSteps;
import io.restassured.response.Response;
import suport.domain.Produto;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ProdutoApi {

    private final String PRODUCT_ENDPOINT = "/produtos";

    public Response cadastrarProduto(Produto produto) {
        return given().header("Authorization", LoginSteps.token).
                body(produto).
            when().
                post(PRODUCT_ENDPOINT);
    }

    public Response pegarProdutoPorId(String id_produto) {
        return given().
                header("Authorization", LoginSteps.token).
            when().
                get(PRODUCT_ENDPOINT + "/" + id_produto);
    }

    public Response pegarListaDeProdutos() {
        return when().get(PRODUCT_ENDPOINT);
    }

    public Response atualizarProduto(String id_produto) {
        var produto = Produto.builder().nome("Bola Furada").descricao("Uma bola de futebol furada").build();
        return given().
                header("Authorization", LoginSteps.token).
                body(produto).
            when().
                put(PRODUCT_ENDPOINT + "/" + id_produto);
    }

    public Response deletarProduto(String id_produto) {
        return given().
                header("Authorization", LoginSteps.token).
            when().
                delete(PRODUCT_ENDPOINT + "/" + id_produto);
    }
}
