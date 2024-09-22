package br.com.postech.service_usuario.application.ports.persistence.impl;

import br.com.postech.service_usuario.adapters.out.SpringDataDynamoDbUsuarioRepository;
import br.com.postech.service_usuario.application.ports.persistence.UsuarioRepositoryPort;
import br.com.postech.service_usuario.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryPort {

    @Autowired
    private SpringDataDynamoDbUsuarioRepository springDataDynamoDbUsuarioRepository;

    public UsuarioRepositoryImpl(SpringDataDynamoDbUsuarioRepository springDataDynamoDbUsuarioRepository) {
        this.springDataDynamoDbUsuarioRepository = springDataDynamoDbUsuarioRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        return springDataDynamoDbUsuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findById(UUID id) {
        return springDataDynamoDbUsuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> findByDocumento(String documento) {
        return springDataDynamoDbUsuarioRepository.findByDocumento(documento);
    }

    @Override
    public Optional<Usuario> findByCrm(String crm) {
        return springDataDynamoDbUsuarioRepository.findByCrm(crm);
    }

    @Override
    public void deleteById(UUID id) {
         springDataDynamoDbUsuarioRepository.deleteById(String.valueOf(id));
    }

    @Override
    public List<Usuario> findAll() {
        return springDataDynamoDbUsuarioRepository.findAll();
    }
}
