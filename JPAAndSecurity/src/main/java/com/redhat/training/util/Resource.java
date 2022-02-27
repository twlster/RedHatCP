package com.redhat.training.util;


public class Resource {

  @PersistenceContext
  @Produces
  private EntityManager em;
}
