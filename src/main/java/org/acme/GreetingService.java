package org.acme;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
public class GreetingService {
  public String greeting(String name) {
    return "Hello friendo: " + name;
  }
}
