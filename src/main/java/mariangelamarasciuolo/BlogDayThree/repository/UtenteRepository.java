package mariangelamarasciuolo.BlogDayThree.repository;

import mariangelamarasciuolo.BlogDayThree.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository <Utente, Integer> {
    Optional<Utente> findByEmail(String ics);
}
