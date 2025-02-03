package org.incsoft.kakfaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
 
@Component
public class KafkaMessageSender {

	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;
	public Mono<ServerResponse> sendMessage(String topicName, String msg) {
		kafkaTemplate.send(topicName,"1", msg);
		kafkaTemplate.send(topicName,"2", msg+" second");
		kafkaTemplate.send(topicName,"3", msg+" third");
	    return ServerResponse.ok().body(Mono.just("SUCCESS"),String.class);
	    		
	}
	
}
