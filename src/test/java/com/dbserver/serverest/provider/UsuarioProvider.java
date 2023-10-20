package com.dbserver.serverest.provider;

import com.dbserver.serverest.DTO.Usuario;

import java.util.UUID;

public class UsuarioProvider {

    String emailRandom = gerarEmailAleatorio();


    public static String gerarEmailAleatorio() {
        String uuid = UUID.randomUUID().toString();
        String email = uuid + "@example.com";
        return email;
    }

    public Usuario providerUsuario(){
        Usuario user = new Usuario(
                "Juliana Nogueira",
                emailRandom,
                "senha",
                "true"
                );
        return user;
    }


}
