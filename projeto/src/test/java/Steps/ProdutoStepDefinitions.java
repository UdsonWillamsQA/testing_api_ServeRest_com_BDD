package Steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import resources.domain.Produto;
import suport.api.ProdutoApi;

import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class ProdutoStepDefinitions {

    private Response response;
    private ProdutoApi produtoApi;
    private Produto produto;
    private String validacao;
    public static String id_produto;

    public ProdutoStepDefinitions(){
        produtoApi = new ProdutoApi();
        produto = Produto.builder().build();

    }

    @Quando("os dados para cadastramento de produto forem enviados")
    public void queEuEnvieOsDadosParaCadastramentoDeProduto() {
        response = produtoApi.cadastrarTipo(produto);
    }

    @Entao("o sistema deve conseguir realizar o cadastro de um produto")
    public void devoConseguirRealizarOCadastroDoProduto() {
        response.then().statusCode(HttpStatus.SC_CREATED);
        id_produto = response.jsonPath().getString("_id");
        validacao = response.body().jsonPath().getString("message");
        Assert.assertEquals("Cadastro realizado com sucesso", validacao);
    }

    @Quando("o usuario requisita a lista de produtos")
    public void euRequisiteAListaDeProdutos() {
        response = produtoApi.pegarListaDeProdutos();
    }

    @Entao("deve conseguir uma lista de {word} produtos")
    public void devoConseguirUmaListaDeProdutos(String tamanho) {
        Assert.assertEquals(tamanho, response.body().jsonPath().getString("quantidade"));
    }

    @Dado("meu produto esteja cadastrado")
    public void meuProdutoEstejaCadastrado() {
        response = produtoApi.pegarProdutoPorId(id_produto);
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Quando("o usuario requisita a edicao do produto")
    public void euRequisiteAEdicaoDoProduto() {
        response = produtoApi.atualizarProduto(id_produto);
    }

    @Entao("o sistema deve conseguir atualizar o produto")
    public void devoConseguirEditarUmProduto() {
        response.then().statusCode(HttpStatus.SC_OK);

        validacao = response.body().jsonPath().getString("message");
        Assert.assertEquals("Registro alterado com sucesso", validacao);


        response = produtoApi.pegarProdutoPorId(id_produto);
        Assert.assertEquals("Bola Furada", response.jsonPath().getString("nome"));
        Assert.assertEquals("Uma bola de futebol furada", response.jsonPath().getString("descricao"));
    }

    @Quando("o usuario solicitar que um produto seja deletado pelo id")
    public void euRequisiteADelecaoDoProduto() {
        response = produtoApi.deletarProduto(id_produto);
    }

    @Entao("o sistema deve conseguir deletar o produto")
    public void devoConseguirDeletarOProduto() {
        response.then().statusCode(HttpStatus.SC_OK);

        validacao = response.body().jsonPath().getString("message");

        Assert.assertEquals("Registro excluído com sucesso", validacao);
    }

    @Dado("^que o produto esteja cadastrado$")
    public void queOProdutoEstejaCadastrado() {
    }

    @Dado("^que existam produtos cadastrados$")
    public void queExistamProdutosCadastrados() {
    }

    /*@Dado("eu tenha algum produto cadastrado")
    public void queEuTenhaAlgumProdutoCadastrado() {
        assertThat(response, is(not(empty())));
    }*/
}
