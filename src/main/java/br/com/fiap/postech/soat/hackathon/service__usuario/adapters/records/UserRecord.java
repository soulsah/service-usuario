package br.com.fiap.postech.soat.hackathon.service__usuario.adapters.records;

import br.com.fiap.postech.soat.hackathon.service__usuario.domain.enums.TipoUsuario;

public record UserRecord(String id, TipoUsuario tipoUsuario, String nome, String documento, String email, String crm){
}
