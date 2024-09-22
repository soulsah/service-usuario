package br.com.postech.service_usuario.application.ports.persistence;

import br.com.postech.service_usuario.domain.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepositoryPort {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(UUID id);
    Optional<Usuario> findByDocumento(String documento);
    Optional<Usuario> findByCrm(String crm);
    void deleteById(UUID id);
    List<Usuario> findAll();
}
