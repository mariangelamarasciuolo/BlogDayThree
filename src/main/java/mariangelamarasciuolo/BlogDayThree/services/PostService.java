package mariangelamarasciuolo.BlogDayThree.services;

import mariangelamarasciuolo.BlogDayThree.entities.Post;
import mariangelamarasciuolo.BlogDayThree.exceptions.NotFoundException;
import mariangelamarasciuolo.BlogDayThree.payloads.PostDTO;
import mariangelamarasciuolo.BlogDayThree.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post save(PostDTO body) {
        Post newPost = new Post();
        if (body.cover().equals("")) {
            newPost.setCover("https://picsum.photos/200/300");
        } else {
            newPost.setCover(body.cover());
        }
        newPost.setTitolo(body.titolo());
        newPost.setContenuto(body.contenuto());
        return postRepository.save(newPost);
    }

    public Page<Post> getPost(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return postRepository.findAll(pageable);
    }

    public Post findById(int id) {

        return postRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

    public Post findAndUpdateById(int id, Post body) {
        Post foundP = this.findById(id);

        foundP.setId(id);
        foundP.setCategoria(body.getCategoria());
        foundP.setTitolo(body.getTitolo());
        foundP.setTempoDiLettura(body.getTempoDiLettura());
        foundP.setContenuto(body.getContenuto());
        return foundP;

    }


    public void findAndDeleteById(int id) {
        Post foundP = this.findById(id);
        postRepository.delete(foundP);
    }
}
