package com.redhat.training.todo.model;

@Entity
@XmlRootElement
public class User {

  @Id
  @GeneratedValue(strategy = GeneratedType.IDENTITY)
  private Long id;

  private String username;

  public Long getId () {

    return id;
  }

  public void setId (Long id) {

    this.id = id;
  }

  public String getUsername () {

    return username;
  }

  public void setUsername (String username) {

    this.username = username;
  }

}
