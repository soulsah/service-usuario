package br.com.postech.service_usuario;

import br.com.postech.service_usuario.application.ports.persistence.UsuarioRepositoryPort;
import br.com.postech.service_usuario.application.service.UsuarioService;
import br.com.postech.service_usuario.domain.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {


    @Mock
    private UsuarioRepositoryPort usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private UUID userId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();
        usuario = new Usuario(userId, "tipoUsuario", "Nome", "documento", "email", "crm");
    }

    @Test
    void salvarUsuario_DeveRetornarUsuarioSalvo() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.salvarUsuario(usuario);

        assertNotNull(resultado);
        assertEquals(usuario, resultado);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void obterUsuarioPorId_DeveRetornarUsuario() {
        when(usuarioRepository.findById(userId)).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.obterUsuarioPorId(userId);

        assertTrue(resultado.isPresent());
        assertEquals(usuario, resultado.get());
        verify(usuarioRepository, times(1)).findById(userId);
    }

    @Test
    void obterUsuarioPorId_DeveRetornarVazioSeNaoExistir() {
        when(usuarioRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<Usuario> resultado = usuarioService.obterUsuarioPorId(userId);

        assertTrue(resultado.isEmpty());
        verify(usuarioRepository, times(1)).findById(userId);
    }

    @Test
    void obterUsuarioPorDocumento_DeveRetornarUsuario() {
        when(usuarioRepository.findByDocumento("documento")).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.obterUsuarioPorDocumento("documento");

        assertTrue(resultado.isPresent());
        assertEquals(usuario, resultado.get());
        verify(usuarioRepository, times(1)).findByDocumento("documento");
    }

    @Test
    void obterUsuarioPorCrm_DeveRetornarUsuario() {
        when(usuarioRepository.findByCrm("crm")).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.obterUsuarioPorCrm("crm");

        assertTrue(resultado.isPresent());
        assertEquals(usuario, resultado.get());
        verify(usuarioRepository, times(1)).findByCrm("crm");
    }

    @Test
    void obterTodosUsuarios_DeveRetornarListaUsuarios() {
        List<Usuario> usuarios = Arrays.asList(usuario);
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> resultado = usuarioService.obterTodosUsuarios();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals(usuario, resultado.get(0));
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void deletarUsuario_DeveChamarRepositoryDeletar() {
        doNothing().when(usuarioRepository).deleteById(userId);

        usuarioService.deletarUsuario(userId);

        verify(usuarioRepository, times(1)).deleteById(userId);
    }

    @Test
    void atualizarUsuario_DeveRetornarUsuarioAtualizado() {
        Usuario usuarioAtualizado = new Usuario(userId, "novoTipo", "Novo Nome", "novoDocumento", "novoEmail", "novoCrm");

        when(usuarioRepository.findById(userId)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuarioAtualizado);

        Usuario resultado = usuarioService.atualizarUsuario(userId, usuarioAtualizado);

        assertNotNull(resultado);
        assertEquals(usuarioAtualizado.getNome(), resultado.getNome());
        assertEquals(usuarioAtualizado.getEmail(), resultado.getEmail());
        verify(usuarioRepository, times(1)).findById(userId);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void atualizarUsuario_DeveLancarExcecaoSeUsuarioNaoExistir() {
        Usuario usuarioAtualizado = new Usuario(userId, "novoTipo", "Novo Nome", "novoDocumento", "novoEmail", "novoCrm");

        when(usuarioRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.atualizarUsuario(userId, usuarioAtualizado);
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(usuarioRepository, times(1)).findById(userId);
        verify(usuarioRepository, times(0)).save(usuario);
    }
}
