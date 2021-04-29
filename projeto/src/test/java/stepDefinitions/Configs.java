package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import suport.api.LoginApi;
import suport.api.ProdutoApi;
import suport.api.UsuarioApi;
import suport.domain.Produto;
import suport.domain.User;

import static io.restassured.RestAssured.when;
import static stepDefinitions.ProdutoSteps.id_produto;
import static stepDefinitions.UserSteps.idUsario;

public class Configs {

    User user;
    UsuarioApi userApi;
    LoginApi loginApi;
    Response response;
    ProdutoApi produtoApi;
    Produto produto;

    public Configs(){
        userApi = new UsuarioApi();
        user = User.builder().administrador("true").build();
        loginApi = new LoginApi();
        produtoApi = new ProdutoApi();
        produto = Produto.builder().build();
    }

    @Before
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = ("http://localhost:3000");
        //A especificação do request
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
    }

    @Before("@criaELogaUsuario")
    public void setupCriaUsuario(){
        setup();
        response = userApi.cadastrarUsuario(user);
        idUsario = response.body().jsonPath().getString("_id");
        response = loginApi.fazLoginResponse();
        LoginSteps.token = response.body().jsonPath().getString("authorization");
    }

    @Before("@CadastraProduto")
    public void setupCadastraProduto(){
        setup();
        response = produtoApi.cadastrarProduto(produto);
        response.then().statusCode(HttpStatus.SC_CREATED);
        id_produto = response.body().jsonPath().getString("_id");
    }

    private void excluirUsuario(){
        when().
            delete("/usuarios" + "/" + idUsario).then().statusCode(HttpStatus.SC_OK);
    }

    @After("@excluirUsuario")
    public void excluirUsuarioEProduto(){
        response = produtoApi.deletarProduto(id_produto);
        excluirUsuario();
    }
}