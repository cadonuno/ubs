package pl.ricardopereira.ubs.In;

import pl.ricardopereira.ubs.out.Message;

public abstract class InputConverter {

	public static Message getMessage(MessageWrite input) {
		Message message = new Message();
		message.setDestiny(input.getDestiny());
		message.setMessage(input.getMessage());
		return message;
	}

}
