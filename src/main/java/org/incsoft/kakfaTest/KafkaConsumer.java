  package org.incsoft.kakfaTest;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	@KafkaListener(topics = "Test1", groupId = "full_load_group")
	public void listenToGroupFullLoad1(String message) {
	    System.out.println("Received Message in group listenToGroupFullLoad1: " + message);
	    try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@KafkaListener(topics = "Test1", groupId = "full_load_group")
	public void listenToGroupFullLoad2(String message) {
	    System.out.println("Received Message in group listenToGroupFullLoad2: " + message);
	}
	
	@KafkaListener(topics = "Test1", groupId = "full_load_group")
	public void listenToGroupFullLoad3(String message) {
	    System.out.println("Received Message in group listenToGroupFullLoad3: " + message);
	}
	@KafkaListener(topics = "Test1", groupId = "full_load_group")
	public void listenToGroupFullLoad4(String message) {
	    System.out.println("Received Message in group listenToGroupFullLoad4: " + message);
	    try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
