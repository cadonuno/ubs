package pl.ricardopereira.ubs.tests;

import static org.junit.Assert.*;

import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
 
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import pl.ricardopereira.ubs.In.MessageRead;
import pl.ricardopereira.ubs.In.MessageWrite;
import pl.ricardopereira.ubs.memorydb.MockDb;
import pl.ricardopereira.ubs.out.Message;
import pl.ricardopereira.ubs.out.MessageList;
import pl.ricardopereira.ubs.out.SendReturn;
import pl.ricardopereira.ubs.services.SmsService;
import pl.ricardopereira.ubs.tests.context.TestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class SmsMessageControllerTest {
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;	
	
	private SmsService serviceMock = Mockito.mock(SmsService.class);

	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
					.webAppContextSetup(context)
					.build();
	}
	
	@Test
	public void whenPostCalledInsertRecord() throws Exception {
		MessageWrite message = new MessageWrite();
		message.setDestiny("message receiver");
		message.setMessage("a message");
		
		SendReturn result = new SendReturn();
		result.setMessageSent(true);
      
        when(serviceMock.sendMessage(message)).thenReturn(result);
 
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/sendmessage/sms")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(mapper.writeValueAsString(message)))
                .andExpect(status().isOk());
 
        verify(serviceMock, times(1)).sendMessage(message);
        verifyNoMoreInteractions(serviceMock);
	}
	
	@Test
	public void whenGetCalledShowRecords() throws Exception {
		Message message = new Message();
		message.setDestiny("message receiver");
		message.setMessage("a message");
		
		MessageList result = new MessageList();
		result.setMessages(Arrays.asList(message));
      
        when(serviceMock.readMessages(new MessageRead())).thenReturn(result);
	}

	
	@Test
	public void whenPostCalledInsertRecordIntegration() throws Exception {
		Message message = new Message();
		message.setDestiny("message receiver");
		message.setMessage("a message");
		
		ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/sendmessage/sms")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(mapper.writeValueAsString(message)))
                .andExpect(status().isOk());
		
		verify(serviceMock, times(1)).readMessages(new MessageRead());
		verifyNoMoreInteractions(serviceMock);
		
		assertThat(MockDb.getSmsLog().listMessages().isEmpty(), is(false));
	}

}
