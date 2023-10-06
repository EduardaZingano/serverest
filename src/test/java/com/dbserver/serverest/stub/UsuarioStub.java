package com.dbserver.serverest.stub;

import com.dbserver.serverest.model.Usuario;

import java.util.UUID;

public class UsuarioStub {

    private String idUsuario;

    String emailRandom = gerarEmailAleatorio();


    public Usuario stubUsuario(){
        Usuario user = new Usuario(
                "Eduarda Zingano",
                emailRandom,
                "senha",
                "true");
        return user;
    }

    public static String gerarEmailAleatorio() {
        String uuid = UUID.randomUUID().toString();
        String email = uuid + "@example.com";
        return email;
    }

    public Usuario stubUsuarioJaCriado(){
        Usuario user = new Usuario(
                "Eduarda Zingano",
                "duda@email.com",
                "senha",
                "true");
        return user;
    }
    public Usuario stubUsuarioPut(){
        Usuario user = new Usuario(
                "Duda Silva",
                "duda@email.com",
                "senha",
                "true");
        return user;
    }

    public String getUsuarioId() {
        return this.idUsuario;
    }

    public String setIdUsuario(String idUsuario) {
        return this.idUsuario = idUsuario;
    }

}
