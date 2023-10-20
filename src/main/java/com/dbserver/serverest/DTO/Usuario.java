package com.dbserver.serverest.DTO;

    public class Usuario {
        private String nome;
        private String email;
        private String password;
        private String administrador;

        private String idUsuario;

        public Usuario(String nome, String email, String password, String administrador) {
            this.nome = nome;
            this.email = email;
            this.password = password;
            this.administrador = administrador;
        }

        public String getNome() {
            return nome;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getAdministrador() {
            return administrador;
        }

        public String getUsuarioId() {return idUsuario; }


    }

