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
//        if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
//        try {
            em.persist(p);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            em.getTransaction().rollback();
//        }
    }

    @Override
    public void edit(Posting p) {
//        if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
//        try {
            em.merge(p);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            em.getTransaction().rollback();
//        }
    }

    @Override
    public void remove(Long id) {
//        if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
//        try {
            Posting i = em.find(Posting.class, id);
            em.remove(id);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            em.getTransaction().rollback();
//        }
    }

    @Override
    public List<Posting> findAll() {
//        if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
//        try {
            Query q = em.createNamedQuery("Posting.findAll", Posting.class);
            return (List<Posting>) q.getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//            em.getTransaction().rollback();
//        }
//        return null;
    }

    @Override
    public List<Posting> findByUser(String username) {
//         if (!em.getTransaction().isActive()) {
//            em.getTransaction().begin();
//        }
//        try {
            Query q = em.createNamedQuery("Posting.findByUserName", Posting.class);
            q.setParameter("username", username);
            return (List<Posting>) q.getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//            em.getTransaction().rollback();
//        }
//        return null;
    }
}
