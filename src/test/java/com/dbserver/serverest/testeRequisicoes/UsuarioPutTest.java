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

public class UsuarioPutTest {

    private static final String URL = "https://serverest.dev";
    private UsuarioProvider usuarioProvider;

    @BeforeEach
    void setup(){
        RestAssured.baseURI = URL;
        usuarioProvider = new UsuarioProvider();
    }

    @Test
    @DisplayName("Deve atualizar um usuário existente")
    void testAtualizaUsuario(){
        Usuario novoUsuario = usuarioProvider.providerUsuario();
        Response responsePost = given()
                .baseUri(URL)
                .contentType(ContentType.JSON)
                .body(novoUsuario)
                .when()
                .post("/usuarios");

        String idUsuario = responsePost.jsonPath().getString("_id");

        Usuario usuarioAtualizado = usuarioProvider.providerUsuarioPut();

        Response response = given()
                .baseUri(URL)
                .contentType(ContentType.JSON)
                .body(usuarioAtualizado)
                .when()
                .put("/usuarios/" + idUsuario);

        assertEquals(200, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("Registro alterado com sucesso"));

        Response responseGet = given()
                .baseUri(URL)
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
