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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;

import completo.projeto.completo.entities.Numeros;
import completo.projeto.completo.repository.NumeroRepository;
import completo.projeto.completo.Controller.NumeroController;
import completo.projeto.completo.Service.NumeroService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class) 
public class NumeroControllerTest {

    @Autowired
    private MockMvc mockMvc;
    

    @InjectMocks
    private NumeroController numeroController;

    @Mock
    private NumeroService numeroService;

    @BeforeEach
    public void setUp() {
        
    }

    @TestConfiguration
public class SecurityTestConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
            .csrf(csrf -> csrf.disable());
        return http.build();
    }
}

    @Test
    public void testFindAll() {
        // Criando um usuário fictício
        Numeros numeros = new Numeros();
        numeros.setNumeroA(3);
        numeros.setNumeroB(5);
        
       
        // Configurando o comportamento do mock
        when(numeroService.findAll()).thenReturn(List.of(numeros));
        
       numeroService.salvar(3, 5);


        // Chamando o método a ser testado
        List<Numeros> numero = numeroController.numeros();
        assertNotNull(numeros);
        assertEquals(1, numero.size());
        assertEquals(3, numero.get(0).getNumeroA());

       
    }

    @Test
    public void soma() throws Exception {
        // Simula comportamento do serviço para evitar exceções
        when(numeroService.soma(anyInt())).thenReturn(15);

       int numero = numeroController.soma(1).getBody();
        assertEquals(15, numero);
    }


    @Test
    public void salvar_DeveRetornarSucesso_QuandoParametrosValidos() throws Exception {
        // Simula comportamento do serviço para evitar exceções
        doNothing().when(numeroService).salvar(anyInt(), anyInt());

        mockMvc.perform(post("/numeros") // Atualize com seu endpoint real
                .param("numeroA", "5")
                .param("numeroB", "10"))
               // .with(user("william123").password("william123"))
             //   .with(csrf())
                .andExpect(status().isOk())
                .andExpect(content().string("Salvo com sucesso"));
    }

    @Test
    public void salvar_DeveRetornarErro_QuandoServicoFalha() throws Exception {
        // Simula erro no serviço
        doThrow(new RuntimeException("Erro ao salvar")).when(numeroService).salvar(anyInt(), anyInt());

        mockMvc.perform(post("/numeros") // Atualize com seu endpoint real
                .param("numeroA", "5")
                .param("numeroB", "10"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Erro ao salvar"));
    }
    }
    

