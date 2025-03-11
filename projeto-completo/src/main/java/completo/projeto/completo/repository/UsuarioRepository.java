package completo.projeto.completo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import completo.projeto.completo.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findAll();

     Usuario findByNome(String nome);

     Usuario  findBySenha(String senha);

     Usuario  findById(int id);

     Usuario findByRole( String role);
  
}
