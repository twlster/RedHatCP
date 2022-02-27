package com.redhat.training.ejb;


import com.redhat.training.model.Employee;
import com.redhat.training.model.Manager;
import com.redhat.training.util.EmployeeLogger;

import java.util.List;

import static com.redhat.training.util.EmployeeLogger.Operation;


@Stateless
public class EmployeeBean {

  @Inject
  private EntityManager em;

  @Inject
  private EmployeeLogger logger;

  public void createEmployee(Employee e){
    em.persist(e);
    logger.logAction(e, Operation.Create);
  }

  public Employee readEmployeeById(Long id){
    Employee employee = em.find(Employee.class, id);
    logger.logAction(employee, Operation.Read);
    return employee;
  }

  public void updateEmployee(Employee e){
    em.merge(e);
    logger.logAction(e, Operation.Update);
  }

  public void deleteEmployee(Employee e){
    em.remove(e);
    logger.logAction(e, Operation.Delete);
  }

  public List<Employee> findAllForManager(Manager manager){
    TypedQuery<Employee> query = em.createNamedQuery("findAllForManager",Employee.class);
    query.setParameter("managerId", manager.getId());
    return query.getResultList();
  }

}
