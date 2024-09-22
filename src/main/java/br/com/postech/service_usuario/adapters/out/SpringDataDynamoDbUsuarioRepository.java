package br.com.postech.service_usuario.adapters.out;

import br.com.postech.service_usuario.domain.Usuario;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@EnableScan
@Repository
@Primary
public interface SpringDataDynamoDbUsuarioRepository extends CrudRepository<Usuario, String> {

    Optional<Usuario> findByDocumento(String documento);

    Optional<Usuario> findByCrm(String crm);

    @Override
    List<Usuario> findAll();

    Optional<Usuario> findById(UUID id);
}