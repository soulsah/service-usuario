package br.com.postech.service_usuario.adapters.in;

import br.com.postech.service_usuario.application.service.UsuarioService;
import br.com.postech.service_usuario.domain.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cadastrar")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuario")
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.salvarUsuario(usuario);
    }

    @GetMapping("/usuario/{id}")
    public Optional<Usuario> obterUsuarioPorId(@PathVariable UUID id) {
        return usuarioService.obterUsuarioPorId(id);
    }

    @GetMapping("/usuario/documento/{documento}")
    public Optional<Usuario> obterUsuarioPorDocumento(@PathVariable String documento) {
        return usuarioService.obterUsuarioPorDocumento(documento);
    }

    @GetMapping("/usuario/crm/{crm}")
    public Optional<Usuario> obterUsuarioPorCrm(@PathVariable String crm) {
        return usuarioService.obterUsuarioPorCrm(crm);
    }

    @GetMapping("/usuario")
    public List<Usuario> obterTodosUsuarios() {
        return usuarioService.obterTodosUsuarios();
    }

    @PutMapping("/usuario/{id}")
    public Usuario atualizarUsuario(@PathVariable UUID id, @RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(id, usuario);
    }

    @DeleteMapping("/usuario/{id}")
    public void deletarUsuario(@PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
    }
}
