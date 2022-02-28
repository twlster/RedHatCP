package com.redhat.training.todo.messaging;

@Stateless
public class JMSClient {

  @Inject
  private JMSContext context;

  @Resource(mappedName = "java:jboss/jms/queue/TodoListQueue")
  private Queue queue;

  public void sendMessage(String msg){
    JMSProducer producer = context.createProducer();
    TextMessage message = context.createTextMessage(msg);
    producer.send(queue, message);
  }

}
