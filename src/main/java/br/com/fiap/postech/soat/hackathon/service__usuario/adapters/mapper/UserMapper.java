package br.com.fiap.postech.soat.hackathon.service__usuario.adapters.mapper;

import br.com.fiap.postech.soat.hackathon.service__usuario.adapters.records.UserRecord;
import br.com.fiap.postech.soat.hackathon.service__usuario.domain.model.User;

public class UserMapper {

    public static UserRecord mapToRecord(User user){
        return new UserRecord(
                user.getId(),
                user.getTipoUsuario(),
                user.getNome(),
                user.getDocumento(),
                user.getEmail(),
                user.getCrm()
        );
    }

    public static User mapFromRecord(UserRecord userRecord){
        return new User(
                userRecord.id(),
                userRecord.tipoUsuario(),
                userRecord.nome(),
                userRecord.documento(),
                userRecord.email(),
                userRecord.crm()
        );
    }

}
