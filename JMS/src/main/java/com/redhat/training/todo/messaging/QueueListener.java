package com.redhat.training.todo.messaging;


import java.io.IOException;


@MessageDriven(name="QueueListener", activationConfig={
    @ActivationConfigProperty(propertyName="destinationLookup", propertyValue="queue/TodoListQueue"),
    @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
})
public class QueueListener implements MessageListener{

  @Override
  public void onMessage(Message msg){
    try{
      if(msg instanceof TextMessage){
        TextMessage message = (TextMessage)msg ;
        writeMessageToFile(message.getText());
      }
    }catch(JMSException e){
      e.printStackTrace();
    }
  }

  public void writeMessageToFile(String message){
    try{
      String filename = "file";
      FileWrite fw = new FileWrite(filename, true);
      fw.write(message);
      fw.write("\n");
      fw.clode();
    }catch(IOException ex){
      System.err.println("IOException: "+ex.getMessage());
    }
  }

}
