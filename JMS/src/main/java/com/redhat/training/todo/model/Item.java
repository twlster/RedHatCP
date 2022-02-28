package com.redhat.training.todo.model;

@Entity
@XmlRootElement
public class Item {

  @Id
  @GeneratedValue(strategy = GeneratedType.IDENTITY)
  private Long id;

  @NotNull
  @Size(min=5, max=50)
  private String description;

  private Boolean done = false;

  @ManyToOne
  @JoinColumn(name="user_id")
  private User user;

  public Long getId () {

    return id;
  }


  public void setId (Long id) {

    this.id = id;
  }


  public String getDescription () {

    return description;
  }


  public void setDescription (String description) {

    this.description = description;
  }


  public Boolean getDone () {

    return done;
  }


  public void setDone (Boolean done) {

    this.done = done;
  }


  public User getUser () {

    return user;
  }


  public void setUser (User user) {

    this.user = user;
  }

}
