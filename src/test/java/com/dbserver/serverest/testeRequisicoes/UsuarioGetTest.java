package com.dbserver.serverest.testeRequisicoes;

import com.dbserver.serverest.DTO.Usuario;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.dbserver.serverest.provider.UsuarioProvider;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UsuarioGetTest {

    private static final String URL = "https://serverest.dev";
    private static UsuarioProvider usuarioProvider;

    @BeforeAll
    static void setup(){
        RestAssured.baseURI = URL;
        usuarioProvider = new UsuarioProvider();
    }

    @Test
    @DisplayName("Deve retornar uma lista com os usuários")
    void testRetornaUsuarios(){
        Response response = given()
                .when()
                .get("/usuarios");

        response.then()
                .statusCode(200)
                .body("usuarios[0].email", not(empty()))
                .body("usuarios[0].password", not(empty()));

    }

    @Test
    @DisplayName("Deve buscar um usuário por ID")
    void testBuscaUsuarioPorId() {
        Usuario usuarioPost = usuarioProvider.providerUsuario();

        Response responsePost = given()
                .contentType(ContentType.JSON)
                .body(usuarioPost)
                .when()
                .post("/usuarios");

        String idUsuario = responsePost.jsonPath().getString("_id");

        Response responseGet = given()
                .when()
                .get("/usuarios/" + idUsuario);

        responseGet.then()
                .statusCode(200)
                .body("email", equalTo(usuarioPost.getEmail()))
                .body("password", equalTo(usuarioPost.getPassword()))
                .body("_id", equalTo(idUsuario));

    }
}
