package br.com.postech.service_usuario.config;

import br.com.postech.service_usuario.application.ports.persistence.UsuarioRepositoryPort;
import br.com.postech.service_usuario.application.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BeanConfiguration {

    @Bean
    public UsuarioService usuarioService(UsuarioRepositoryPort usuarioRepository) {
        return new UsuarioService(usuarioRepository);
    }

}
