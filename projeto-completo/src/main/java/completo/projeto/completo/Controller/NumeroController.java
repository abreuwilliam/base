package completo.projeto.completo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import completo.projeto.completo.Exception.RecursoNaoEncontradoException;
import completo.projeto.completo.Exception.RequisicaoInvalidaException;
import completo.projeto.completo.Service.NumeroService;
import completo.projeto.completo.entities.Numeros;

@RestController
@CrossOrigin(origins = "*") 
@RequestMapping(value = "/numeros")
public class NumeroController {

    @Autowired
    private NumeroService numeroService;

    @GetMapping()
    public ResponseEntity<List<Numeros>> numeros() {
        List<Numeros> lista = numeroService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/soma")
    public ResponseEntity<?> soma(@RequestParam int id) {
        if (id < 0) {
            throw new RequisicaoInvalidaException("O id deve ser positivo.");
        }
        if (numeroService.findById(id) == null) {
                throw new RecursoNaoEncontradoException("Número não encontrado.");
                }
            return ResponseEntity.ok(numeroService.soma(id));
        
    }

    @PostMapping()
    public ResponseEntity<String> salvar(@RequestParam int numeroA, @RequestParam int numeroB) {
        if (numeroA < 0 || numeroB < 0) {
                throw new RequisicaoInvalidaException("Os números devem ser positivos.");
            }
     numeroService.salvar(numeroA, numeroB);
     
    return ResponseEntity.status(HttpStatus.CREATED).body("Salvo com sucesso");
    }

    @DeleteMapping()
    public ResponseEntity<String> deletar(@RequestParam int id) {
            numeroService.deletar(id);
            return ResponseEntity.ok("Deletado com sucesso");
       
    }

    @PutMapping()
    public ResponseEntity<String> atualizar(@RequestParam int id, @RequestParam int numeroA, @RequestParam int numeroB) {
            numeroService.atualizar(id, numeroA, numeroB);
            return ResponseEntity.ok("Atualizado com sucesso");
    }
}
