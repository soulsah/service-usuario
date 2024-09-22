package br.com.postech.service_usuario;

import br.com.postech.service_usuario.adapters.in.UsuarioController;
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

public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    private Usuario usuario;
    private UUID userId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();
        usuario = new Usuario(userId, "tipoUsuario", "Nome", "documento", "email", "crm");
    }

    @Test
    void criarUsuario_DeveRetornarUsuarioCriado() {
        when(usuarioService.salvarUsuario(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioController.criarUsuario(usuario);

        assertNotNull(resultado);
        assertEquals(usuario, resultado);
        verify(usuarioService, times(1)).salvarUsuario(usuario);
    }

    @Test
    void obterUsuarioPorId_DeveRetornarUsuario() {
        when(usuarioService.obterUsuarioPorId(userId)).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioController.obterUsuarioPorId(userId);

        assertTrue(resultado.isPresent());
        assertEquals(usuario, resultado.get());
        verify(usuarioService, times(1)).obterUsuarioPorId(userId);
    }

    @Test
    void obterUsuarioPorId_DeveRetornarNotFoundSeNaoExistir() {
        when(usuarioService.obterUsuarioPorId(userId)).thenReturn(Optional.empty());

        Optional<Usuario> resultado = usuarioController.obterUsuarioPorId(userId);

        assertTrue(resultado.isEmpty());
        verify(usuarioService, times(1)).obterUsuarioPorId(userId);
    }

    @Test
    void obterUsuarioPorDocumento_DeveRetornarUsuario() {
        when(usuarioService.obterUsuarioPorDocumento("documento")).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioController.obterUsuarioPorDocumento("documento");

        assertTrue(resultado.isPresent());
        assertEquals(usuario, resultado.get());
        verify(usuarioService, times(1)).obterUsuarioPorDocumento("documento");
    }

    @Test
    void obterUsuarioPorCrm_DeveRetornarUsuario() {
        when(usuarioService.obterUsuarioPorCrm("crm")).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioController.obterUsuarioPorCrm("crm");

        assertTrue(resultado.isPresent());
        assertEquals(usuario, resultado.get());
        verify(usuarioService, times(1)).obterUsuarioPorCrm("crm");
    }

    @Test
    void obterTodosUsuarios_DeveRetornarListaUsuarios() {
        List<Usuario> usuarios = Arrays.asList(usuario);
        when(usuarioService.obterTodosUsuarios()).thenReturn(usuarios);

        List<Usuario> resultado = usuarioController.obterTodosUsuarios();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals(usuario, resultado.get(0));
        verify(usuarioService, times(1)).obterTodosUsuarios();
    }

    @Test
    void atualizarUsuario_DeveRetornarUsuarioAtualizado() {
        when(usuarioService.atualizarUsuario(userId, usuario)).thenReturn(usuario);

        Usuario resultado = usuarioController.atualizarUsuario(userId, usuario);

        assertNotNull(resultado);
        assertEquals(usuario, resultado);
        verify(usuarioService, times(1)).atualizarUsuario(userId, usuario);
    }

    @Test
    void deletarUsuario_DeveChamarServicoDeletar() {
        doNothing().when(usuarioService).deletarUsuario(userId);

        usuarioController.deletarUsuario(userId);

        verify(usuarioService, times(1)).deletarUsuario(userId);
    }
}
