package completo.projeto.completo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import completo.projeto.completo.entities.Numeros;
import completo.projeto.completo.repository.NumeroRepository;
import completo.projeto.completo.Service.NumeroService;




@ExtendWith(MockitoExtension.class) // ✅ Necessário para ativar Mockito no JUnit 5
public class NumeroServiceTest {

    @InjectMocks
    private NumeroService numeroService; 

    @Mock
    private NumeroRepository numeroRepository;

    

    @BeforeEach
    public void setUp() {
        
    }

    @Test
    public void testFindAll() {
        // Criando um usuário fictício
        Numeros numeros = new Numeros();
        numeros.setNumeroA(3);
        numeros.setNumeroB(5);
        

        // Configurando o comportamento do mock
        when(numeroRepository.findAll()).thenReturn(List.of(numeros));
        
        
        // Chamando o método a ser testado
        List<Numeros> numero = numeroService.findAll();
        assertNotNull(numeros);
        assertEquals(1, numero.size());
        assertEquals(3, numero.get(0).getNumeroA());

       
    }
       
}
