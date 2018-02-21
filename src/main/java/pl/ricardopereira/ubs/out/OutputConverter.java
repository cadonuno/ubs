package pl.ricardopereira.ubs.out;

import java.util.List;

public abstract class OutputConverter {

	public static MessageList getMessageList(List<Message> list) {
		MessageList messageList = new MessageList();
		messageList.setMessages(list);
		return messageList;
	}

}
