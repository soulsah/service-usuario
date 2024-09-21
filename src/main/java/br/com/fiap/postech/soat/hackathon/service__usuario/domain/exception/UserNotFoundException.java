package br.com.fiap.postech.soat.hackathon.service__usuario.domain.exception;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(){
        super("Usuário não foi encontrado.");
    }
}
