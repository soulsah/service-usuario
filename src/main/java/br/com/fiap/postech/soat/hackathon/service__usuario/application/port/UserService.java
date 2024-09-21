package br.com.fiap.postech.soat.hackathon.service__usuario.application.port;

import br.com.fiap.postech.soat.hackathon.service__usuario.adapters.records.UserRecord;
import br.com.fiap.postech.soat.hackathon.service__usuario.domain.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    UserRecord createUser(UserRecord userRecord);

    UserRecord updateUser(Long id, UserRecord userRecord) throws UserNotFoundException;

    UserRecord updateUserMedico(Long id, UserRecord userRecord) throws UserNotFoundException;

    void deleteUser(Long id);

    List<UserRecord> findAllUsers() throws UserNotFoundException;

    UserRecord findUserById(Long id) throws UserNotFoundException;

    UserRecord findUserByCrm(String crm) throws UserNotFoundException;

    UserRecord findUserByDocumento(String documento) throws UserNotFoundException;
}
