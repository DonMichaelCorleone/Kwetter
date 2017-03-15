/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.loek.kwetter.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import nl.loek.kwetter.model.Posting;
import nl.loek.kwetter.model.User;

@Stateless
public class PostingDaoJPAImp implements PostingDAO {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    public PostingDaoJPAImp() {
    }

    @Override
    public Boolean createPosting(Posting p) {
        try {
            em.persist(p);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean editPosting(Posting p) {
        try {
            em.merge(p);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Posting findPosting(Long id) {
        return em.find(Posting.class, id);
    }

    @Override
    public Boolean removePosting(Long id) {
        try {
            Posting i = em.find(Posting.class, id);
            em.remove(id);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Posting> findAll() {
        Query q = em.createNamedQuery("Posting.findAll", Posting.class);
        return (List<Posting>) q.getResultList();
    }

    @Override
    public List<Posting> findByUser(User u) {
        Query q = em.createNamedQuery("Posting.findByUserName", Posting.class);
        q.setParameter("author", u);
        return (List<Posting>) q.getResultList();
    }
}
