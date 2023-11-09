package mariangelamarasciuolo.BlogDayThree.controllers;

import mariangelamarasciuolo.BlogDayThree.entities.Utente;
import mariangelamarasciuolo.BlogDayThree.exceptions.BadRequestException;
import mariangelamarasciuolo.BlogDayThree.payloads.UtenteDTO;
import mariangelamarasciuolo.BlogDayThree.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping("")
    public Page<Utente> getUtente(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy) {

        return utenteService.getUtente(page, size, orderBy);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Utente saveUtente(@RequestBody @Validated UtenteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return utenteService.save(body);
    }

    @GetMapping("/{id}")
    public Utente findById(@PathVariable int id) {
        return utenteService.findById(id);
    }

    @PutMapping("/{id}")
    public Utente findAndUpdateById(@PathVariable int id, @RequestBody Utente body) {
        return utenteService.findAndUpdateById(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDeleteById(@PathVariable int id) {
        utenteService.findAndDeleteById(id);
    }
}
