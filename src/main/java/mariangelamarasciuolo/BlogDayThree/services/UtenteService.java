package mariangelamarasciuolo.BlogDayThree.services;

import mariangelamarasciuolo.BlogDayThree.entities.Utente;
import mariangelamarasciuolo.BlogDayThree.exceptions.BadRequestException;
import mariangelamarasciuolo.BlogDayThree.exceptions.NotFoundException;
import mariangelamarasciuolo.BlogDayThree.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ListIterator;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    public Utente save(Utente body){
        utenteRepository.findByEmail(body.getEmail()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });

        body.setAvatar("http://ui-avatars.com/api/?name=" + body.getName() + "+" + body.getSurname());

        return utenteRepository.save(body);
    }
    public Page<Utente> getUtente(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return utenteRepository.findAll(pageable);
    }

    public Utente findById(int id) {
        return utenteRepository.findById(id).orElseThrow(()->new NotFoundException(id));

    }

    public Utente findAndUpdateById(int id, Utente body){
        Utente found = this.findById(id);
                found.setId(id);
                found.setName(body.getName());
                found.setSurname(body.getSurname());
        return found;
    }

    public void findAndDeleteById (int id){
        Utente found = this.findById(id);
        utenteRepository.delete(found);
    }
}
