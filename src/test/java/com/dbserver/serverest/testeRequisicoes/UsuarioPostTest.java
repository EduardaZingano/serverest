package com.dbserver.serverest.testeRequisicoes;


import com.dbserver.serverest.DTO.Usuario;
import com.dbserver.serverest.provider.UsuarioProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsuarioPostTest {

    private final String URL = "https://serverest.dev";
    private UsuarioProvider usuarioProvider;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = URL;
        usuarioProvider = new UsuarioProvider();
    }

    @Test
    @DisplayName("Deve cadastrar um novo usuário")
    void cadastraNovoUsuario() {
        Usuario usuarioPost = usuarioProvider.providerUsuario();
        Response responsePost = given()
                .contentType(ContentType.JSON)
                .body(usuarioPost)
                .when()
                .post("/usuarios");

        assertEquals(201, responsePost.getStatusCode());
        assertTrue(responsePost.getBody().asString().contains("Cadastro realizado com sucesso"));

    }

    @Test
    @DisplayName("Deve retornar erro ao cadastrar um usuário com email que já exista")
    void testCadastraUsuarioComEmailRepetido(){

        Usuario usuarioPost = usuarioProvider.providerUsuarioJaCriado();
        Response responsePost = given()
                .baseUri(URL)
                .contentType(ContentType.JSON)
                .body(usuarioPost)
                .when()
                .post("/usuarios");

        Response responsePost2 = given()
                .baseUri(URL)
                .contentType(ContentType.JSON)
                .body(usuarioPost)
                .when()
                .post("/usuarios");

        assertEquals(400, responsePost2.getStatusCode());

    }
}
