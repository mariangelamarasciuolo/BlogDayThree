package mariangelamarasciuolo.BlogDayThree.repository;

import mariangelamarasciuolo.BlogDayThree.entities.Post;
import mariangelamarasciuolo.BlogDayThree.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findByEmail(String ics);
}
