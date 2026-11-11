
package template;

import builder.EmailMessageBuilder;
import model.EmailAttachment;
import model.EmailMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailTemplateService {

    private final Map<String, EmailTemplate> templates = new HashMap<>();

    public void registerTemplate(EmailTemplate template) {
        if (templates.containsKey(template.getName())) {
            throw new IllegalArgumentException(
                    "Já existe um template com esse nome."
            );
        }

        templates.put(template.getName(), template);
    }

    public EmailTemplate getTemplate(String name) {
        EmailTemplate template = templates.get(name);

        if (template == null) {
            throw new IllegalArgumentException(
                    "Template não encontrado: " + name
            );
        }

        return template;
    }

    public EmailMessage createEmailMessage(
            String templateName,
            String sender,
            List<String> recipients,
            List<EmailAttachment> attachments) {

        EmailTemplate template = getTemplate(templateName);

        EmailMessageBuilder builder = new EmailMessageBuilder()
                .setSender(sender)
                .setSubject(template.getSubject())
                .setContent(template.getContent())
                .setContentType(template.getContentType());

        for (String recipient : recipients) {
            builder.addRecipient(recipient);
        }

        for (EmailAttachment attachment : attachments) {
            builder.addAttachment(attachment);
        }

        return builder.build();
    }
}
