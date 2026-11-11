
package observer;

import model.EmailEvent;
import model.EmailSendResult;

import java.util.ArrayList;
import java.util.List;

public class EmailLogObserver implements EmailObserver {

  private final List<EmailSendResult> emailLogs = new ArrayList<>();

  @Override
  public void update(EmailEvent event) {
    emailLogs.add(event.getResult());

    System.out.println("Log de envio registrado.");
  }

  public List<EmailSendResult> getEmailLogs() {
    return new ArrayList<>(emailLogs);
  }
}
