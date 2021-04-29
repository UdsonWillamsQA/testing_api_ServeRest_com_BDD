package stepDefinitions;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import suport.domain.Carrinho;
import suport.api.CarrinhoApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class CarrinhoSteps {

    CarrinhoApi carrinhoApi;
    List<Carrinho> produtosCarrinho;
    Map<String, List> body;
    Response response;
    String validacao;
    public static String id_carrinho;
    String quantCarrinhos;

    public CarrinhoSteps() {
        carrinhoApi = new CarrinhoApi();
        produtosCarrinho = new ArrayList<>();
        body = new HashMap<>();
    }

    @Quando("os dados forem enviados para o cadastramento do carrinho")
    public void euEnvieOsDadosParaCadastramentoDeCarrinho() {
        var carrinho1 = Carrinho.builder().idProduto(ProdutoSteps.id_produto).quantidade(1).build();
        var carrinho2 = Carrinho.builder().idProduto("K6leHdftCeOJj8BJ").quantidade(10).build();

        produtosCarrinho.add(carrinho1);
        produtosCarrinho.add(carrinho2);
        body.put("produtos", produtosCarrinho);
        response = carrinhoApi.cadastrarCarrinho(body);
        id_carrinho = response.body().jsonPath().getString("_id");
        validacao = response.body().jsonPath().getString("message");

    }

    @Entao("deve realizar o cadastro de um carrinho")
    public void devoConseguirRealizarOCadastroDeUmCarrinho() {
        response.then().statusCode(HttpStatus.SC_CREATED);
        Assert.assertEquals("Cadastro realizado com sucesso", validacao);
    }

    @E("a quantidade de carrinhos deve ser {word}")
    public void aQuantidadeDeCarrinhosDeveSer(String quantCarrinhosEsperada) {
        response = carrinhoApi.pegaCarrinhos();
        quantCarrinhos = response.body().jsonPath().getString("quantidade");

        Assert.assertEquals(quantCarrinhosEsperada, quantCarrinhos);
    }

    @Dado("que o usuario queira um carrinho pelo seu id")
    public void queEuQueiraUmCarrinhoEspecifico() {

    }

    @Quando("seja fornecido o id especifico do carrinho")
    public void euForneçaUmCarrinhoEspecifico() {
        response = carrinhoApi.pegarCarrinho();
    }

    @Entao("o usuario deve conseguir um carrinho")
    public void euDevoConseguirUmCarrinho() {
        response.then().statusCode(HttpStatus.SC_OK);
        assertThat(response, is(not(empty())));
    }

    @Quando("o usuario tenha concluido a compra")
    public void queEuTenhaConcluidoACompra() {
        response = carrinhoApi.concluirCompra();
    }

    @Entao("deve receber uma mensagem de exclusao com sucesso")
    public void devoReceberUmaMensagemDeExclusaoComSucesso() {
        response.then().statusCode(HttpStatus.SC_OK);
        validacao = response.body().jsonPath().getString("message");

        Assert.assertEquals("Registro excluído com sucesso", validacao);
    }

    @Quando("o usuario tenha cancelado a compra")
    public void queEuTenhaCanceladoACompra() {
        response = carrinhoApi.cancelarCompra();
    }

    /*Já que o carrinho é concluido no step acima, aqui ele não vai existir
    Logo não é possivel exclui-lo. Então fiz a asserção de que o carrinho não deve ser encontrado. */
    @Entao("deve receber uma mensagem de carrinho nao encontrado para esse usuario" )
    public void devoFinalizarACompra() {
        response.then().statusCode(HttpStatus.SC_OK);
        validacao = response.body().jsonPath().getString("message");

        Assert.assertEquals("Não foi encontrado carrinho para esse usuário", validacao);
    }

    @Dado("^que o usuario desista de fazer uma compra$")
    public void queSeDesistaDeFazerUmaCompra() {
    }

    @Dado("^que o usuario finalizou suas buscas por produtos$")
    public void queOUsuarioFinalizouSuasBuscasPorProdutos() {
    }
}
