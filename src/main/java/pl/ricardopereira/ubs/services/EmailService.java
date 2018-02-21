package pl.ricardopereira.ubs.services;

import pl.ricardopereira.ubs.In.InputConverter;
import pl.ricardopereira.ubs.In.MessageRead;
import pl.ricardopereira.ubs.In.MessageWrite;
import pl.ricardopereira.ubs.interfaces.IMessageService;
import pl.ricardopereira.ubs.memorydb.MockDb;
import pl.ricardopereira.ubs.out.MessageList;
import pl.ricardopereira.ubs.out.OutputConverter;
import pl.ricardopereira.ubs.out.SendReturn;

public class EmailService implements IMessageService {

	@Override
	public SendReturn sendMessage(MessageWrite message) {
		SendReturn result = new SendReturn();
		result.setMessageSent(false);
		if (message != null) {
			try {
				MockDb.getEmailLog().saveMessage(InputConverter.getMessage(message));
				result.setMessageSent(true);
			} catch (Exception e) {
				//some exception that would happen when sending the actual message
			}
		}
		return result;
	}

	@Override
	public MessageList readMessages(MessageRead toRead) {
		return OutputConverter.getMessageList(MockDb.getEmailLog().listMessages());
	}

}
