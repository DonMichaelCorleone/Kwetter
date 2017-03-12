/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.loek.kwetter.dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import nl.loek.kwetter.model.Posting;

@Stateless
//@JPA
//@LocalBean
public class PostingDaoJPAImp implements PostingDAO {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;


    public PostingDaoJPAImp() {
    }

    @Override
    public void create(Posting p) {
            em.persist(p);
    }

    @Override
    public void edit(Posting p) {
            em.merge(p);
    }

    @Override
    public void remove(Long id) {
            Posting i = em.find(Posting.class, id);
            em.remove(id);
    }

    @Override
    public List<Posting> findAll() {
            Query q = em.createNamedQuery("Posting.findAll", Posting.class);
            return (List<Posting>) q.getResultList();
    }

    @Override
    public List<Posting> findByUser(String username) {
            Query q = em.createNamedQuery("Posting.findByUserName", Posting.class);
            q.setParameter("username", username);
            return (List<Posting>) q.getResultList();
    }
}
