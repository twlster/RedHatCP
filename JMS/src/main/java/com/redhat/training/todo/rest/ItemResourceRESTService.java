package com.redhat.training.todo.rest;


import com.redhat.training.todo.data.ItemRepository;
import com.redhat.training.todo.data.UserRepository;
import com.redhat.training.todo.model.Item;
import com.redhat.training.todo.model.User;
import com.redhat.training.todo.service.ItemService;
import com.redhat.training.todo.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path("/items")
@RequestScoped
public class ItemResourceRESTService {

  @Inject
  private Logger log;

  @Context
  private HttpServletRequest request;

  private User currentUser;

  @Inject
  private ItemRepository itemRepository;

  @Inject
  private UserRepository userRepository;

  @Inject
  private ItemService itemService;

  @Inject
  private UserService userService;

  @PostContruct
  public void setUser(){
    String login = "";
    Principal principal = request.getUserPrincipal();

    if(principal != null) {
      login = principal.getName();
    }else{
      login = "Guest";
    }
    currentUser = userRepository.getByName(login);

    if(currentUser == null){
      currentUser = new User();
      currentUser.setUsername(login);
      userService.addUser(currentUser);
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Item> listAllItems(){
    return itemRepository.findAllItemsForUser(currentUser);
  }

  @GET
  @Path("/}id:[0-9][0-9]*}")
  public Item lookupItemById(@PathParam("id") long id){
    Item item = itemRepository.findById(id);
    if(item==null){
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    return item;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createItem(Item item){
    item.setUser(currentUser);
    Response.ResponseBuilder builder = null;

    try{
      if(item.getId() == null){
        itemService.register(item);
        builder = Response.ok();
      } else {
        Item itemToUpdate = lookupItemById(item.getId());
        itemToUpdate.setDescription(item.getDescription());
        itemToUpdate.setDone(item.getDone());
        itemService.update(itemToUpdate);
        builder = Response.ok();
      }
    }catch(Exception e){
      Map<String, String> responseObj = new HashMap<>();
      responseObj.put("error", e.getMessage());
      builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }
    return builder.build;
  }

  @DELETE
  @Path("{id}")
  public void deleteItem(@PathParam("id") long id){
    itemService.remove(id);
  }

}
