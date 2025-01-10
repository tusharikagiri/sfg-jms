package guru.springframework.sfg_jms.sender;

import java.util.UUID;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.sfg_jms.config.JmsConfig;
import guru.springframework.sfg_jms.model.HelloWorldMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class HelloSender {

	private final JmsTemplate jmsTemplate;
	private final ObjectMapper objectMapper;

	@Scheduled(fixedRate = 2000)
	public void sendMessage() {

		HelloWorldMessage message = HelloWorldMessage.builder().id(UUID.randomUUID()).message("Hello World").build();

		jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

	}

	@Scheduled(fixedRate = 3000)
	public void sendAndReceiveMessage() throws JMSException {

		HelloWorldMessage message = HelloWorldMessage.builder().id(UUID.randomUUID()).message("Hello World").build();

		Message receivedMsg = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RCV_QUEUE, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				Message helloMessage = null;
				
				try {
					helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
					helloMessage.setStringProperty("_type", "guru.springframework.sfg_jms.model.HelloWorldMessage");
					return helloMessage;
				} catch (JsonProcessingException e) {
					
				} catch (JMSException e) {
					throw e;
				}
				return helloMessage;
			}
		});
		System.out.println("Reply: " + receivedMsg.getBody(String.class));
	}
}
