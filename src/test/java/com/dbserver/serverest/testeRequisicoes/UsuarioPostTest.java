package com.dbserver.serverest.testeRequisicoes;


import com.dbserver.serverest.model.Usuario;
import com.dbserver.serverest.stub.UsuarioStub;
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

    private final String url = "https://serverest.dev";
    private UsuarioStub usuarioStub = new UsuarioStub();

    @BeforeEach
    void setup() {
        RestAssured.baseURI = url;
        usuarioStub = new UsuarioStub();
    }

    @Test
    @DisplayName("Deve cadastrar um novo usuário")
    void cadastraNovoUsuario() {
        Usuario usuarioPost = usuarioStub.stubUsuario();
        Response responsePost = given()
                .contentType(ContentType.JSON)
                .body(usuarioPost)
                .when()
                .post("/usuarios");

        assertEquals(201, responsePost.getStatusCode());
        usuarioStub.setIdUsuario(responsePost.jsonPath().getString("_id"));
        assertTrue(responsePost.getBody().asString().contains("Cadastro realizado com sucesso"));

    }

    @Test
    @DisplayName("Deve retornar erro ao cadastrar um usuário com email que já exista")
    void testCadastraUsuarioComEmailRepetido(){

        Usuario usuarioPost = usuarioStub.stubUsuarioJaCriado();
        Response responsePost = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(usuarioPost)
                .when()
                .post("/usuarios");

        Response responsePost2 = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(usuarioPost)
                .when()
                .post("/usuarios");

        assertEquals(400, responsePost2.getStatusCode());

    }
}
