package nl.loek.kwetter.dao;

import java.util.List;
import nl.loek.kwetter.model.Posting;
import nl.loek.kwetter.model.User;

public interface PostingDAO {

    Boolean createPosting(Posting p);

    Boolean editPosting(Posting p);

    Boolean removePosting(Long id);

    List<Posting> findAll();

    List<Posting> findByUser(User u);

    Posting findPosting(Long id);
}
