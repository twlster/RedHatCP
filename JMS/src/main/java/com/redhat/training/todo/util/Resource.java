package com.redhat.training.todo.util;


public class Resource {

  @Produces
  @PersistenceContext
  private EntityManager em;

  @Produces
  public Logger produceLog(InjectionPoint injectionPoint){
    return Logger.getLogger(injectionPoint.getMember.getDeclaringClass().getName());
  }

}
