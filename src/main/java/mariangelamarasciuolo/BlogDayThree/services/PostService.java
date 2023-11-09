package mariangelamarasciuolo.BlogDayThree.services;

import mariangelamarasciuolo.BlogDayThree.entities.Post;
import mariangelamarasciuolo.BlogDayThree.exceptions.NotFoundException;
import mariangelamarasciuolo.BlogDayThree.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    public Post save(Post body){
        Random rndm = new Random();
        body.setId(rndm.nextInt(1, 1000));
        body.setCover("https://picsum.photos/200/300");
        return postRepository.save(body);
    }
    public Page<Post> getPost(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return postRepository.findAll(pageable);
    }

    public Post findById(int id) {

        return postRepository.findById(id).orElseThrow(()-> new NotFoundException(id));

    }

    public Post findAndUpdateById(int id, Post body){
        Post foundP = this.findById(id);

                foundP.setId(id);
                foundP.setCategoria(body.getCategoria());
                foundP.setTitolo(body.getTitolo());
                foundP.setTempoDiLettura(body.getTempoDiLettura());
                foundP.setContenuto(body.getContenuto());
                return foundP;

        }



    public void findAndDeleteById (int id){
        Post foundP =  this.findById(id);
        postRepository.delete(foundP);
    }
}
