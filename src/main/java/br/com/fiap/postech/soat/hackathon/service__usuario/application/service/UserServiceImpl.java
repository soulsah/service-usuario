package br.com.fiap.postech.soat.hackathon.service__usuario.application.service;

import br.com.fiap.postech.soat.hackathon.service__usuario.adapters.mapper.UserMapper;
import br.com.fiap.postech.soat.hackathon.service__usuario.application.persistence.UserRepository;
import br.com.fiap.postech.soat.hackathon.service__usuario.adapters.records.UserRecord;
import br.com.fiap.postech.soat.hackathon.service__usuario.application.port.UserService;
import br.com.fiap.postech.soat.hackathon.service__usuario.domain.exception.UserNotFoundException;
import br.com.fiap.postech.soat.hackathon.service__usuario.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRecord createUser(UserRecord userRecord) {
        User user = UserMapper.mapFromRecord(userRecord);
        User saveUser = userRepository.save(user);
        return UserMapper.mapToRecord(saveUser);
    }

    @Override
    public UserRecord updateUser(Long id, UserRecord userRecord) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        user.setTipoUsuario(userRecord.tipoUsuario());
        user.setNome(userRecord.nome());
        user.setDocumento(userRecord.documento());
        user.setEmail(userRecord.email());
        User updateUser = userRepository.save(user);
        return UserMapper.mapToRecord(user);
    }

    @Override
    public UserRecord updateUserMedico(Long id, UserRecord userRecord) throws UserNotFoundException {
        User medico = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        medico.setTipoUsuario(userRecord.tipoUsuario());
        medico.setNome(userRecord.nome());
        medico.setDocumento(userRecord.documento());
        medico.setEmail(userRecord.email());
        medico.setCrm(userRecord.crm());
        User updateUser = userRepository.save(medico);
        return UserMapper.mapToRecord(medico);
    }

    @Override
    public void deleteUser(Long id) {
        try {
            userRepository.findById(id)
                    .orElseThrow(UserNotFoundException::new);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<UserRecord> findAllUsers() throws UserNotFoundException {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }
        return users.stream()
                .map(UserMapper::mapToRecord)
                .collect(Collectors.toList());
    }

    @Override
    public UserRecord findUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return UserMapper.mapToRecord(user);
    }

    @Override
    public UserRecord findUserByCrm(String crm) throws UserNotFoundException {
        User user = userRepository.findByCrm(crm)
                .orElseThrow(UserNotFoundException::new);
        return UserMapper.mapToRecord(user);
    }

    @Override
    public UserRecord findUserByDocumento(String documento) throws UserNotFoundException {
        User user = userRepository.findByDocumento(documento)
                .orElseThrow(UserNotFoundException::new);
        return UserMapper.mapToRecord(user);
    }
}
