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

public class UsuarioPutTest {

    private static final String url = "https://serverest.dev";
    private UsuarioStub usuarioStub;

    @BeforeEach
    void setup(){
        RestAssured.baseURI = url;
        usuarioStub = new UsuarioStub();
    }

    @Test
    @DisplayName("Deve atualizar um usuário existente")
    void testAtualizaUsuario(){
        Usuario novoUsuario = usuarioStub.stubUsuario();
        Response responsePost = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(novoUsuario)
                .when()
                .post("/usuarios");

        String idUsuario = responsePost.jsonPath().getString("_id");

        Usuario usuarioAtualizado = usuarioStub.stubUsuarioPut();

        Response response = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(usuarioAtualizado)
                .when()
                .put("/usuarios/" + idUsuario);

        assertEquals(200, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("Registro alterado com sucesso"));

        Response responseGet = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .when()
                .get("/usuarios/" + idUsuario);

        assertEquals(200, responseGet.getStatusCode());
        assertEquals(usuarioAtualizado.getNome(), responseGet.jsonPath().getString("nome"));
        assertEquals(usuarioAtualizado.getEmail(), responseGet.jsonPath().getString("email"));
        assertEquals(usuarioAtualizado.getPassword(), responseGet.jsonPath().getString("password"));
        assertEquals(usuarioAtualizado.getAdministrador(), responseGet.jsonPath().getString("administrador"));
    }

}
