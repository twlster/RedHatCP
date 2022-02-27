package com.redhat.training.util;


import com.redhat.training.model.Employee;

import java.io.FileWriter;
import java.io.IOException;


@Stateless
public class EmployeeLogger {

  public enum Operation {Create, Read, Update, Delete};

  public void logAction(Employee employee, Operation operation){
    String message = operation.toString()+" Employee: "+employee;
    writeMessageToFile(message);
  }

  private void writeMessageToFile(String message){
    try{
      System.out.println("Logging: "+message);
      String filename = "/EmployeeLog.txt";
      FileWriter fw = new FileWriter(filename, true);
      fw.write(message);
      fw.write("\n");
      fw.close();
    } catch(IOException e){
      System.err.println("IOException: "+e.getMessage());
    }
  }

}
