package com.dbserver.serverest.testeRequisicoes;

import com.dbserver.serverest.DTO.Usuario;
import com.dbserver.serverest.provider.UsuarioProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UsuarioPutTest {

    private static final String URL = "https://serverest.dev";
    private static UsuarioProvider usuarioProvider;

    @BeforeAll
     static void setup(){
        RestAssured.baseURI = URL;
        usuarioProvider = new UsuarioProvider();
    }

    @Test
    @DisplayName("Deve atualizar um usuário existente")
    void testAtualizaUsuario(){
        Usuario novoUsuario = usuarioProvider.providerUsuario();
        Response responsePost = given()
                .contentType(ContentType.JSON)
                .body(novoUsuario)
                .when()
                .post("/usuarios");

        String idUsuario = responsePost.jsonPath().getString("_id");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(novoUsuario)
                .when()
                .put("/usuarios/" + idUsuario);

        response.then()
                .statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"));

        Response responseGet = given()
                .when()
                .get("/usuarios/" + idUsuario);

        responseGet.then()
                .statusCode(200)
                .body("nome", equalTo(novoUsuario.getNome()))
                .body("email", equalTo(novoUsuario.getEmail()))
                .body("password", equalTo(novoUsuario.getPassword()))
                .body("administrador", equalTo(novoUsuario.getAdministrador()));
    }

    @Test
    @DisplayName("Deve cadastrar um novo usuário ao tentar alterar um usuário inexistente por ID")
    void cadastrarNovoUsuarioAoTentarAlterarUsuarioDeIDInexistente() {
        Usuario usuario = usuarioProvider.providerUsuario();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when()
                .put("usuarios/8888888888888888888888888888888888888888888888");

        response.then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"));
    }
}

