package completo.projeto.completo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import completo.projeto.completo.Service.UsuarioService;
import completo.projeto.completo.entities.Usuario;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*") 
@RequestMapping(value = "/consulta")
public class UsuarioController {
    

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public List<Usuario> usuario() {
        return usuarioService.findAll();
    }
    
}
