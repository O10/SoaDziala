package service;

import model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by O10 on 19.05.15.
 */
@Stateless
public class UserDaoService {
    @PersistenceContext
    EntityManager em;

    public User getUserByLogin(String login){
        return (User) this.em.createNamedQuery("User.findByLogin").setParameter("username",login).getSingleResult();
    }

    public void updateUserPass(String username,String newPass){
        User user=new User();
        user.setPasswd(newPass);
        user.setUsername(username);
        this.em.merge(user);
    }

    public List<User> getAllUsers(){
        return this.em.createNamedQuery("User.findAll").getResultList();
    }
}
