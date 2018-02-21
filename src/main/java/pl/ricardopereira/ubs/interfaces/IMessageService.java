package pl.ricardopereira.ubs.interfaces;

import pl.ricardopereira.ubs.In.MessageRead;
import pl.ricardopereira.ubs.In.MessageWrite;
import pl.ricardopereira.ubs.out.MessageList;
import pl.ricardopereira.ubs.out.SendReturn;

public interface IMessageService {
	public SendReturn sendMessage(MessageWrite message);	
	
	public MessageList readMessages(MessageRead toRead);
}
