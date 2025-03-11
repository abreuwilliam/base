package completo.projeto.completo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import completo.projeto.completo.entities.Numeros;
import completo.projeto.completo.entities.Usuario;
import completo.projeto.completo.repository.NumeroRepository;
import completo.projeto.completo.repository.UsuarioRepository;

@Service
public class NumeroService {
    
    

    @Autowired
    private NumeroRepository numeroRepository;

    public List<Numeros> findAll() {
        return numeroRepository.findAll();
    }

    public Numeros findByNumeroA(int numeroA) {
        return numeroRepository.findByNumeroA(numeroA);
    }

    public Numeros findByNumeroB(int numeroB) {
        return numeroRepository.findByNumeroB(numeroB);
    }
    public void salvar(int numeroA, int numeroB) {
        Numeros numeros = new Numeros();
        numeros.setNumeroA(numeroA);
        numeros.setNumeroB(numeroB);
        numeroRepository.save(numeros);
    }
   public void deletar(int id) {
       numeroRepository.deleteById(id);
   }
  public void atualizar(int id, int numeroA, int numeroB) {
      Numeros numeros = new Numeros();
      numeros.setId(id);
      numeros.setNumeroA(numeroA);
      numeros.setNumeroB(numeroB);
      numeroRepository.save(numeros);
  }
    public int soma(int id) {
        Numeros numeros = numeroRepository.findById(id);
        return numeros.getNumeroA() + numeros.getNumeroB();
    }
    
}

