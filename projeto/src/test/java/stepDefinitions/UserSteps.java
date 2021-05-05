package stepDefinitions;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import suport.api.UsuarioApi;
import suport.domain.User;

import static io.restassured.RestAssured.when;
import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class UserSteps {

    private Response response;
    private UsuarioApi usuarioApi;
    private User user;
    private String validacao;
    public static String idUsario;
    private String idDelete;

    public UserSteps(){
        usuarioApi = new UsuarioApi();
        user = User.builder().build();
    }

    @Dado("que se tenha um serviço de cadastro de usuários")
    public void euTenhaUmServiçoDeCadastroDeUsuários() {

    }

    @Quando("se enviar os nomes dos {word} com {word} para o cadastramento de usuario")
    public void seEnviarOsNomesDosUsuariosComEmailParaOCadastramentoDeUsuario(String nome, String email) {
        user.setNome(nome);
        user.setEmail(email);
        response = usuarioApi.cadastrarUsuario(user);
        idUsario = response.body().jsonPath().getString("_id");
    }

    @Quando("se enviar os dados para cadastramento de usuario")
    public void euEnvioOsDadosParaCadastramentoDeUsuario() {
        response = usuarioApi.cadastrarUsuario(user);
        idUsario = response.body().jsonPath().getString("_id");
    }

    @Entao("deve realizar o cadastro de usuario")
    public void devoConseguirRealizarOCadastroDeUsuario() {
        response.then().statusCode(HttpStatus.SC_CREATED);
        validacao = response.body().jsonPath().getString("message");
        Assert.assertEquals("Cadastro realizado com sucesso", validacao);
    }

    @Dado("que a aplicação possua usuarios cadastrados")
    public void queAAplicacaoPossuaUsuariosCadastrados(){
    }

    @Quando("pesquisar por usuarios")
    public void pesquisarPorUsuarios() {
        response = usuarioApi.getAllUsers();
        assertThat(response, is(not(empty())));
    }

    @Entao("o sistema deve receber uma lista de {int} usuarios")
    public void devoConseguirReceberUmaListaDeUsuarios(Integer tamanho) {
        validacao = response.jsonPath().getString("quantidade");
        Assert.assertEquals(tamanho.toString(), validacao);
    }

    @Quando("é solicitado a pesquisa pelo id de um usuario")
    public void pesquisoPeloIdDeUmUsuario() {
        response = usuarioApi.getById(idUsario);
    }

    @Entao("o sistema deve receber os dados de um usuario")
    public void devoConseguirReceberOsDadosDeUmUsuario() {
        User userValidacao = response.then().extract().body().as(User.class);
        Assert.assertEquals(user, userValidacao);
    }

    @Quando("um usuario for editado pelo seu id")
    public void euAlteroUmUsuarioPeloId() {
        response = usuarioApi.alterarUsuario(idUsario);
    }

    @Entao("o sistema deve conseguir receber os dados atualizados de um usuario")
    public void devoConseguirReceberOsDadosAtualizadosDeUsuario() {
        response.then().statusCode(HttpStatus.SC_OK);
        validacao = response.body().jsonPath().getString("message");
        Assert.assertEquals("Registro alterado com sucesso", validacao);
    }

    @Quando("é solicitado que um {word} seja deletado pelo id")
    public void eSolicitadoADeleçãoDeUmUsuarioPeloId(String nomeUsuario) {
        idDelete = usuarioApi.getByName(nomeUsuario);
        response = usuarioApi.deletarPorId(idDelete);
    }

    @Entao("o sistema deve conseguir deletar o usuario")
    public void devoConseguirDeletarOUsuario() {
        validacao = response.jsonPath().getString("message");
        Assert.assertEquals("Registro excluído com sucesso", validacao);
    }

    @Dado("^que o usuario tem acesso ao sistema$")
    public void queOUsuarioTemAcessoAoSistema() {
    }
}

