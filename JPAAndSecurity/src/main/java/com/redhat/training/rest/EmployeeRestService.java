package com.redhat.training.rest;


import com.redhat.training.model.Department;
import com.redhat.training.model.Employee;
import com.redhat.training.model.Manager;

import java.util.List;

@Stateless
@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeRestService {

  @Inject
  private EmployeeBean employeeBean;

  @Inject
  private DepartmentBean departmentbean;

  @Inject
  private ManagerBean managerBean;

  @GET
  @Path("/byId/{Id}")
  @Produces(MediaType.APPLICATION_JSON)
  @PermitAll
  public Employee getEmployee (@PathParam("id") Long id) {

    return employeeBean.readEmployeeById(id);
  }

  @DELETE
  @Path("/byId/{Id}")
  @RolesAllowed({"manager","superuser"})
  public void deleteEmployee (@PathParam("id") Long id) {

    Employee toBeDeleted = employeeBean.readEmployeeById(id);
    employeeBean.deleteEmployee(toBeDeleted);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @RolesAllowed({"manager","superuser"})
  public Response saveEmployee (Employee employee) {

    ResponseBuilder builder;
    if (employee.getId() == null) {

      Employee newEmployee = new Employee();
      newEmployee.setName(employee.getName());

      employeeBean.createEmployee(newEmployee);

      builder = Response.ok();
    } else {

      Employee employeeToUpdate = employeeBean.readEmployeeById(employee.getId());

      if (employeeToUpdate == null) {
        builder = Response.serverError();
      } else {
        employeeBean.updateEmployee(employee);
        builder = Response.ok();
      }
    }

    return builder.build();
  }

  @GET
  @Path("/getByManager/{managerId}")
  @Produces(MediaTypeAPPLICATION_XML)
  @PermitAll
  public List<Employee> getEmployeesForManager (@PathParam("managerId") Long managerId) {
    Manager manager = managerBean.findById(managerId);
    return employeeBean.findAllForManager(manager);
  }

  @POST
  @Path("createDepartment")
  @Consumes(MediaTypeAPPLICATION_JSON)
  @Produces(MediaTypeAPPLICATION_JSON)
  @RolesAllowed({"manager","superuser"})
  public Department createDepartment (Department d) {
    return departmentbean.createDepartment(d);
  }

  @POST
  @Path("createManager")
  @Consumes(MediaTypeAPPLICATION_JSON)
  @Produces(MediaTypeAPPLICATION_JSON)
  @RolesAllowed({"superuser"})
  public Manager createManager (Manager m) {
    return managerBean.createManager(m);
  }

  @POST
  @Path("/assingEmployee/{employeeId}/{departmentId}")
  @RolesAllowed({"manager","superuser"})
  public void assignEmployee (
      @PathParam("employeeId") Long employeeId,
      @PathParam("departmentId") Long departmentId
  ) {
    Department department = departmentbean.findById(departmentId);
    Employee employee = employeeBean.readEmployeeById(employeeId);
    employee.setDepartment(department);
    employeeBean.updateEmployee(employee);
  }

  @POST
  @Path("/assingEmployee/{managerId}/{departmentId}")
  @RolesAllowed({"superuser"})
  public void assignEmployee(@PathParam("managerId") Long managerId,
      @PathParam("departmentId") Long departmentId){
    Department department = departmentbean.findById(departmentId);
    Manager manager = managerBean.findById(managerId);
    manager.setDepartment(department);
    managerBean.updateManager(manager);
  }

}


}
