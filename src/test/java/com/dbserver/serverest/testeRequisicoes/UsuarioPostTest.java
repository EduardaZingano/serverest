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

public class UsuarioPostTest {

    private static final String URL = "https://serverest.dev";
    private static UsuarioProvider usuarioProvider;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = URL;
        usuarioProvider = new UsuarioProvider();
    }

    @Test
    @DisplayName("Deve cadastrar um novo usuário")
    void cadastraNovoUsuario() {
        Usuario usuarioPost = usuarioProvider.providerUsuario();
        Response response = given()
                .contentType(ContentType.JSON)
                .body(usuarioPost)
                .when()
                .post("/usuarios");

        response.then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"));

    }

}
