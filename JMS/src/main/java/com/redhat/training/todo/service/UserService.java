package com.redhat.training.todo.service;


import com.redhat.training.todo.model.User;


@Stateless
public class UserService {

  @Inject
  private Logger log;

  @Inject
  private EntityManager em;

  public void addUser(User u){
    log.info("Adding new user: "+u.getUsername());
    em.persist(u);
  }

}
