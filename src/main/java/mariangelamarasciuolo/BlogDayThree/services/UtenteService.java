package mariangelamarasciuolo.BlogDayThree.services;

import mariangelamarasciuolo.BlogDayThree.entities.Utente;
import mariangelamarasciuolo.BlogDayThree.exceptions.BadRequestException;
import mariangelamarasciuolo.BlogDayThree.exceptions.NotFoundException;
import mariangelamarasciuolo.BlogDayThree.payloads.UtenteDTO;
import mariangelamarasciuolo.BlogDayThree.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Utente save(UtenteDTO body) {
        utenteRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });
        Utente newUtente = new Utente();
        if (body.avatar().equals("")) {
            newUtente.setAvatar("http://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());
        } else {
            newUtente.setAvatar(body.avatar());
        }

        newUtente.setName(body.name());
        newUtente.setSurname(body.surname());
        newUtente.setEmail(body.email());
        newUtente.setDataDiNascita(body.dataDiNascita());
        return utenteRepository.save(newUtente);
    }

    public Page<Utente> getUtente(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return utenteRepository.findAll(pageable);
    }

    public Utente findById(int id) {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

    public Utente findAndUpdateById(int id, Utente body) {
        Utente found = this.findById(id);
        found.setId(id);
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        return found;
    }

    public void findAndDeleteById(int id) {
        Utente found = this.findById(id);
        utenteRepository.delete(found);
    }
}
