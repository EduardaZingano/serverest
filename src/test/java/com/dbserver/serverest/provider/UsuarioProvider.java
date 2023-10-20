package com.dbserver.serverest.provider;

import com.dbserver.serverest.DTO.Usuario;

import java.util.UUID;

public class UsuarioProvider {

    String emailRandom = gerarEmailAleatorio();


    public Usuario providerUsuario(){
        Usuario user = new Usuario(
                "Eduarda Zingano",
                emailRandom,
                "senha",
                "true"
                );
        return user;
    }

    public static String gerarEmailAleatorio() {
        String uuid = UUID.randomUUID().toString();
        String email = uuid + "@example.com";
        return email;
    }

    public Usuario providerUsuarioJaCriado(){
        Usuario user = new Usuario(
                "Eduarda Zingano",
                "duda@email.com",
                "senha",
                "true"
                );
        return user;
    }
    public Usuario providerUsuarioPut(){
        Usuario user = new Usuario(
                "Duda Silva",
                "duda@email.com",
                "senha",
                "true"
                );
        return user;
    }

}
