package com.dbserver.serverest.testeRequisicoes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.dbserver.serverest.DTO.Usuario;
import com.dbserver.serverest.provider.UsuarioProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsuarioDeleteTest {
    private static final String url = "https://serverest.dev";
    private UsuarioProvider usuarioProvider;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = url;
        usuarioProvider = new UsuarioProvider();
    }

    @Test
    @DisplayName("Deve excluir um usuário existente")
    void testExcluiUsuario() {
        Usuario usuarioPost = usuarioProvider.providerUsuario();
        Response responsePost = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(usuarioPost)
                .when()
                .post("/usuarios");

        assertEquals(201, responsePost.getStatusCode());

        String idUsuario = responsePost.jsonPath().getString("_id");

        Response responseDelete = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .when()
                .delete("/usuarios/" + idUsuario);

        assertEquals(200, responseDelete.getStatusCode());
        assertTrue(responseDelete.getBody().asString().contains("Registro excluído com sucesso"));

    }

    @Test
    @DisplayName("Deve retornar que nada foi excluído se o usuário for inexistente")
    void naoExcluiUsuarioQueNaoExiste() {
        Response resposta = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/usuarios/9999999999999999999999999999999999");

        assertEquals(200, resposta.getStatusCode());
        assertTrue(resposta.getBody().asString().contains("Nenhum registro excluído"));
    }

}