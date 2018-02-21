package pl.ricardopereira.ubs.controllers;

import pl.ricardopereira.ubs.In.MessageRead;
import pl.ricardopereira.ubs.In.MessageWrite;
import pl.ricardopereira.ubs.interfaces.IMessageController;
import pl.ricardopereira.ubs.out.MessageList;
import pl.ricardopereira.ubs.out.SendReturn;
import pl.ricardopereira.ubs.services.EmailService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sendmessage/email")
public class EmailMessageController implements IMessageController {
	private EmailService service = new EmailService();
	
	@RequestMapping(method = RequestMethod.POST)
	public SendReturn sendMessage(MessageWrite message) {
		return getService().sendMessage(message);
	}

	@RequestMapping(method = RequestMethod.GET)
	public MessageList readMessages(MessageRead toRead) {
		return getService().readMessages(toRead);
	}

	public EmailService getService() {
		return service;
	}

	public void setService(EmailService service) {
		this.service = service;
	}
}
