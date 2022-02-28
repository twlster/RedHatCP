package com.redhat.training.todo.data;


import com.redhat.training.todo.model.User;

import java.util.List;


@ApplicationScoped
public class UserRepository {

  @Inject
  private EntityManager em;

  public User findById(Long id){
    return em.find(User.class, id);
  }

  public User findByName(String username){
    TypedQuery<User> query =
        em.createQuery("SELECT u from User u where u.username = :username", User.class);
    query.setParameter("username", username);
    try{
      return query.getSingleResult();
    } catch(NoResultException ex){
      return null;
    }
  }

  public List<User> findAllUsers(){
    TypedQuery<User> query = em.createQuery("SELECT u from User u ", User.class);
    return query.getResultList();
  }

}
