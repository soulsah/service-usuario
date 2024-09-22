package br.com.postech.service_usuario.application.service;

import br.com.postech.service_usuario.application.ports.persistence.UsuarioRepositoryPort;
import br.com.postech.service_usuario.domain.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UsuarioService {

    private final UsuarioRepositoryPort usuarioRepository;

    public UsuarioService(UsuarioRepositoryPort usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obterUsuarioPorId(UUID id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> obterUsuarioPorDocumento(String documento) {
        return usuarioRepository.findByDocumento(documento);
    }

    public Optional<Usuario> obterUsuarioPorCrm(String crm) {
        return usuarioRepository.findByCrm(crm);
    }

    public List<Usuario> obterTodosUsuarios() {  // Novo método para obter todos os usuários
        return usuarioRepository.findAll();
    }

    public void deletarUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario atualizarUsuario(UUID id, Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setDocumento(usuarioAtualizado.getDocumento());
            usuario.setCrm(usuarioAtualizado.getCrm());
            usuario.setTipoUsuario(usuarioAtualizado.getTipoUsuario());
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
}
