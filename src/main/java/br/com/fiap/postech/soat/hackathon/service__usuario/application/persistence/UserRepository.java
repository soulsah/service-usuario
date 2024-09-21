package br.com.fiap.postech.soat.hackathon.service__usuario.application.persistence;

import br.com.fiap.postech.soat.hackathon.service__usuario.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByDocumento(String documento);

    Optional<User> findByCrm(String crm);
}
