package completo.projeto.completo.Controller;
import java.util.List;


import completo.projeto.completo.entities.Numeros;
import completo.projeto.completo.Service.NumeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import completo.projeto.completo.Service.UsuarioService;
import completo.projeto.completo.entities.Usuario;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "*") 
@RequestMapping(value = "/numeros")
public class NumeroController {

    @Autowired
    private NumeroService numeroService;

    @GetMapping()
    public List<Numeros> numeros() {
        return numeroService.findAll();
    }

    @GetMapping("/soma")
    public ResponseEntity<Integer> soma(@RequestParam int id) {
        try {
            return ResponseEntity.ok(numeroService.soma(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @PostMapping()
    public ResponseEntity<String> salvar(@RequestParam int numeroA, @RequestParam int numeroB) {
        try {
            numeroService.salvar(numeroA, numeroB);
            return ResponseEntity.ok("Salvo com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar");
        }
    }
    @DeleteMapping()
    public ResponseEntity<String> deletar(@RequestParam int id) {
        try {
            numeroService.deletar(id);
            return ResponseEntity.ok("Deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar");
        }
    }

    @PutMapping
    public ResponseEntity<String> atualizar(@RequestParam int id, @RequestParam int numeroA, @RequestParam int numeroB) {
        try {
            numeroService.atualizar(id, numeroA, numeroB);
            return ResponseEntity.ok("Atualizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar");
        }
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de tipagem: Certifique-se de que os valores são números inteiros válidos.");
    }
}
