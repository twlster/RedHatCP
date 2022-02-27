package com.redhat.training.model;

@XmlRootElement
@Entity
@NamedQuery(name="findAllForManager",
            query="SELECT e FROM Employee e WHERE e.department.manager.id = :managerId")
public class Employee {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name="departmentID")
  private Department department;

  public Long getId () {
    return id;
  }

  public void setId (Long id) {
    this.id = id;
  }

  public String getName () {
    return name;
  }

  public void setName (String name) {
    this.name = name;
  }

  public Department getDepartment () {
    return department;
  }

  public void setDepartment (Department department) {
    this.department = department;
  }
}
