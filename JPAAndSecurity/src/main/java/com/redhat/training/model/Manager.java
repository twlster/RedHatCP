package com.redhat.training.model;

@Entity
public class Manager {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name="departmentID")
  private Department department;

  public Long getId () {
    return id;
  }

  public void setId (Long id) {
    this.id = id;
  }

  @JsonIgnore
  @XmlTransient
  public Department getDepartment () {
    return department;
  }

  public void setDepartment (Department department) {
    this.department = department;
  }
}