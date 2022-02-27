package com.redhat.training.model;


import java.util.Set;


@Entity
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToOne(mappedBy = "department")
  private Manager manager;

  @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
  private Set<Employee> employees;

  public Long getId () {
    return id;
  }

  public String getName () {
    return name;
  }

  public Manager getManager () {
    return manager;
  }

  public Set<Employee> getEmployees () {
    return employees;
  }

  public void setId (Long id) {
    this.id = id;
  }

  public void setName (String name) {
    this.name = name;
  }

  public void setManager (Manager manager) {
    this.manager = manager;
  }

  public void setEmployees (Set<Employee> employees) {
    this.employees = employees;
  }

}
