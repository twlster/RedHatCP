package com.redhat.training.ejb;


import com.redhat.training.model.Department;


@Stateless
public class DepartmentBean {

  @Inject
  private Entitymanager em;

  public Department createDepartment(Department d){
    em.persist(d);
    return d;
  }

  public Department findById(Long id){
    return em.find(Department.class, id);
  }
}
