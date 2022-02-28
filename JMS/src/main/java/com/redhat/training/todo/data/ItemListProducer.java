package com.redhat.training.todo.data;


import com.redhat.training.todo.model.Item;
import com.redhat.training.todo.model.User;

import java.util.List;


@RequestScoped
public class ItemListProducer {

  @Inject
  private ItemRepository itemRepository;

  @Inject
  private UserRepository userRepository;

  private List<Item> items;

  private User user;

  @Produces
  @Named
  public List<Item> getItems(){

  }

  public void onItemListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Item item){
    retrieveAllItems();
  }

  @PostConstruct
  public void init(){
    user = userRepository.findById(1L);
    retrieveAllItems();
  }

  public void retrieveAllItems(){
    items = itemRepository.findAllItemsForUser(user);
  }

}
