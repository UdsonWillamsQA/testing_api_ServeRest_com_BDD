package stepDefinitions;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import suport.api.ProdutoApi;
import suport.domain.Produto;


public class ProdutoSteps {

    private Response response;
    private ProdutoApi produtoApi;
    private Produto produto;
    private String validacao;
    public static String id_produto;
    private String id_dos_produtos;
    public ProdutoSteps() {
        produtoApi = new ProdutoApi();
        produto = Produto.builder().build();
    }

    @Quando("os {word} forem enviados para cadastramento com a {word} e o {word}")
    public void osProdutosForemEnviadosParaCadastramentoComAQuantidadeADescricaoEComOPreco(String nome, String quantidade, String preco) {
        produto.setNome(nome);
        produto.setQuantidade(quantidade);
        produto.setPreco(preco);
        response = produtoApi.cadastrarProduto(produto);
    }

    @Entao("^deve realizar o cadastro de produtos$")
    public void deveRealizarOCadastroDeProdutos() {
        response.then().statusCode(HttpStatus.SC_CREATED);
        id_produto = response.jsonPath().getString("_id");
        validacao = response.body().jsonPath().getString("message");
        Assert.assertEquals("Cadastro realizado com sucesso", validacao);
    }

    @E("deve retornar a lista com o {int} de produtos")
    public void deveRetornarAListaComATotalDeProdutos(Integer total) {
        response = produtoApi.pegarListaDeProdutos();
        validacao = response.body().jsonPath().getString("quantidade");

        Assert.assertEquals(total.toString(), validacao);
    }

    @Quando("os dados para cadastramento de produto forem enviados")
    public void queEuEnvieOsDadosParaCadastramentoDeProduto() {
        response = produtoApi.cadastrarProduto(produto);
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

    @Dado("que o produto esteja cadastrado")
    public void meuProdutoEstejaCadastrado() {
        response = produtoApi.pegarProdutoPorId(id_produto);
        System.out.println(id_produto);
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
        Assert.assertEquals("BolaFurada", response.jsonPath().getString("nome"));
        Assert.assertEquals("Uma bola de futebol furada", response.jsonPath().getString("descricao"));
    }

    /*@Quando("o usuario solicitar que um produto seja deletado pelo id")
    public void euRequisiteADelecaoDoProduto() {
        response = produtoApi.deletarProduto(id_produto);
    }

    @Entao("o sistema deve conseguir deletar o produto")
    public void devoConseguirDeletarOProduto() {
        response.then().statusCode(HttpStatus.SC_OK);

        validacao = response.body().jsonPath().getString("message");

        Assert.assertEquals("Registro excluído com sucesso", validacao);
    }*/

    @Dado("^que existam produtos cadastrados$")
    public void queExistamProdutosCadastrados() {
    }

    @Dado("^que o sistema possua produtos cadastrados$")
    public void queOSistemaPossuaProdutosCadastrados() {
    }

    @Quando("o usuario solicitar que os {word} sejam deletados")
    public void oUsuarioSolicitarQueOsProdutosSejamDeletadosPeloId(String uri) {
        response = produtoApi.pegarProdutoPorUri(uri);
        id_dos_produtos = response.body().jsonPath().getString("produtos._id");
        id_dos_produtos = id_dos_produtos.replaceAll("\\[", "");
        id_dos_produtos = id_dos_produtos.replaceAll("\\]", "");
        response = produtoApi.deletarProduto(id_dos_produtos);
    }

    @Entao("o sistema deve conseguir deletar os produtos")
    public void oSistemaDeveConseguirDeletarOsProdutos() {
        response.then().statusCode(HttpStatus.SC_OK);
        validacao = response.body().jsonPath().getString("message");
        Assert.assertEquals("Registro excluído com sucesso", validacao);
    }


    @Entao("^exclua os dados de usuario$")
    public void excluaOsDadosDeUsuario() {

    }
}
