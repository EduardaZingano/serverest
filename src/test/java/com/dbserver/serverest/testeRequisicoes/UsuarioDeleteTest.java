package com.dbserver.serverest.testeRequisicoes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.dbserver.serverest.DTO.Usuario;
import com.dbserver.serverest.provider.UsuarioProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UsuarioDeleteTest {
    private static final String URL = "https://serverest.dev";
    private static UsuarioProvider usuarioProvider;

    @BeforeAll
   static void setup() {
        RestAssured.baseURI = URL;
        usuarioProvider = new UsuarioProvider();
    }

    @Test
    @DisplayName("Deve excluir um usuário existente")
    void testExcluiUsuario() {
        Usuario usuarioPost = usuarioProvider.providerUsuario();
        Response responsePost = given()
                .contentType(ContentType.JSON)
                .body(usuarioPost)
                .when()
                .post("/usuarios");

        String idUsuario = responsePost.jsonPath().getString("_id");

        Response responseDelete = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/usuarios/" + idUsuario);

        responseDelete.then()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));
    }

    @Test
    @DisplayName("Deve retornar que nada foi excluído se o usuário for inexistente")
    void naoExcluiUsuarioQueNaoExiste() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/usuarios/9999999999999999999999999999999999");

        response.then()
                .statusCode(200)
                .body("message", equalTo("Nenhum registro excluído"));
    }
}