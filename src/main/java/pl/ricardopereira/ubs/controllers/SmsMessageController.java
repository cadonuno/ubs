package pl.ricardopereira.ubs.controllers;

import pl.ricardopereira.ubs.In.MessageRead;
import pl.ricardopereira.ubs.In.MessageWrite;
import pl.ricardopereira.ubs.interfaces.IMessageController;
import pl.ricardopereira.ubs.out.MessageList;
import pl.ricardopereira.ubs.out.SendReturn;
import pl.ricardopereira.ubs.services.SmsService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sendmessage/sms")
public class SmsMessageController implements IMessageController {
	private SmsService service = new SmsService();
	
	@RequestMapping(method = RequestMethod.POST)
	public SendReturn sendMessage(MessageWrite message) {
		return getService().sendMessage(message);
	}

	@RequestMapping(method = RequestMethod.GET)
	public MessageList readMessages(MessageRead toRead) {
		return getService().readMessages(toRead);
	}

	public SmsService getService() {
		return service;
	}

	public void setService(SmsService service) {
		this.service = service;
	}
	
}
