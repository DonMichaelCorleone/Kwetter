package nl.loek.kwetter.dao;

import java.util.List;
import nl.loek.kwetter.model.Posting;

public interface PostingDAO {

    void create(Posting p);
    
    void edit(Posting p);
    
    void remove(Long id);

    List<Posting> findAll();
    
    List<Posting> findByUser(String username);
    
}
