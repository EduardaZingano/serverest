package com.dbserver.serverest.testeRequisicoes;

import com.dbserver.serverest.DTO.Usuario;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.dbserver.serverest.provider.UsuarioProvider;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UsuarioGetTest {

    private static final String URL = "https://serverest.dev";
    private UsuarioProvider usuarioProvider;

    @BeforeEach
    void setup(){
        RestAssured.baseURI = URL;
        usuarioProvider = new UsuarioProvider();
    }

    @Test
    @DisplayName("Deve retornar uma lista com os usuários")
    void testRetornaUsuarios(){
        Response response = given()
                .when()
                .get("/usuarios");
        assertEquals(200, response.getStatusCode());

        String email = response.jsonPath().getString("usuarios[0].email");
        String password = response.jsonPath().getString("usuarios[0].password");

        assertFalse(email.isEmpty());
        assertFalse(password.isEmpty());

    }

    @Test
    @DisplayName("Deve buscar um usuário por ID")
    void testBuscaUsuarioPorId() {
        Usuario usuarioPost = usuarioProvider.providerUsuario();

        Response responsePost = given()
                .baseUri(URL)
                .contentType(ContentType.JSON)
                .body(usuarioPost)
                .when()
                .post("/usuarios");


        String idUsuario = responsePost.jsonPath().getString("_id");

        Response responseGet = given()
                .when()
                .get("/usuarios/" + idUsuario);
        assertEquals(200, responseGet.getStatusCode());


        String email = responseGet.jsonPath().getString("email");
        String senha = responseGet.jsonPath().getString("password");
        String id = responseGet.jsonPath().getString("_id");

        assertEquals(usuarioPost.getEmail(), email);
        assertEquals(usuarioPost.getPassword(), senha);
        assertEquals(idUsuario, id);

    }
}
