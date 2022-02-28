package com.redhat.training.todo.service;


import com.redhat.training.todo.data.ItemRepository;
import com.redhat.training.todo.messaging.JMSClient;
import com.redhat.training.todo.model.Item;


@Stateless
public class ItemService {

  @Inject
  private Logger log;

  @Inject
  private Entitymanager em;

  @Inject
  private ItemRepository repository;

  @Inject
  private Event<Item> itemEventSrc;

  @Inject
  private JMSClient client;

  public void register(Item item) throws Exception{
    log.info("Adding new task: "+ item.getDescription());
    em.persist(item);
    itemEventSrc.fire(item);
  }

  public void remove(Long id){
    em.remove(repository.findById(id));
  }

  public void update(Item item){
    client.sendMessage("Item with ID:"+item.getId()+" was updated with status Done="+item.getDone());
    em.merge(item);
  }

}
