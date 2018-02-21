package pl.ricardopereira.ubs.memorydb;

import java.util.ArrayList;
import java.util.List;

import pl.ricardopereira.ubs.out.Message;
import pl.ricardopereira.ubs.out.MessageList;

public class MemoryDb {
	private List<Message> messages;
	
	public List<Message> listMessages() {
		return messages;
	}
	
	public boolean saveMessage(Message message) {
		try {
			if (messages == null) {
				this.messages = new ArrayList<>();
			}
			
			messages.add(message);	
			return true;
		} catch (Exception e) {
			//do some error logging
			return false;
		}
	}
}
