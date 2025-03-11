package completo.projeto.completo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import completo.projeto.completo.entities.Usuario;
import completo.projeto.completo.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findByNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }

    public Usuario findByRole(String role) {
        return usuarioRepository.findByRole(role);
    }

    public Usuario save(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }
    
}
