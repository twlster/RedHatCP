package com.redhat.training.todo.data;


import com.redhat.training.todo.model.Item;
import com.redhat.training.todo.model.User;

import java.util.List;


@ApplicationScoped
public class ItemRepository {

  @Inject
  private EntityManager em;

  public Item findById(Long id){
    return em.find(Item.class, id);
  }

  public List<Item> findAllItemsForUser(User user){
    TypedQuery<Item> query =
        em.createQuery("SELECT i from Item i where i.user = :user", Item.class);
    query.setParameter("user", user);

    return query.getResultList();
  }
}
