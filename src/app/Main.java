package app;

import adapter.MockEmailAdapter;
import adapter.EmailSender;
import builder.EmailMessageBuilder;
import model.EmailMessage;
import model.EmailSendResult;
import model.ContentType;
import service.MockMailService;

public class Main {

  public static void main(String[] args) {

    MockMailService service = new MockMailService();

    EmailSender sender = new MockEmailAdapter(service);

    EmailMessage message = new EmailMessageBuilder()
        .setSender("teste@email.com")
        .addRecipient("destinatario@email.com")
        .setSubject("Teste do Adapter")
        .setContent("Nosso primeiro envio simulado!")
        .setContentType(ContentType.TEXT)
        .build();

    EmailSendResult result = sender.send(message);

    System.out.println("Status: " + result.getStatus());
  }
}
