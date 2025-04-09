 package completo.projeto.completo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import completo.projeto.completo.entities.Numeros;
import completo.projeto.completo.entities.NumerosDTO;

@Repository
public interface NumeroRepository extends JpaRepository<Numeros, Integer> {

    List<Numeros> findAll();

     Numeros findByNumeroA(int numeroA);

     Numeros  findByNumeroB(int numerob);

     Numeros  findById(int id);

     

   
  
}
 
