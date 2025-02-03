package org.incsoft.kakfaTest;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SpringKafkaDemoApplication {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    
	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaDemoApplication.class, args);
	}


    @Bean
    public ConsumerFactory<String, String> consumerFactory() 
    { 
  
        // Creating a Map of string-object pairs 
        Map<String, Object> config = new HashMap<>(); 
  
        // Adding the Configuration 
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
        		bootstrapAddress); 
        config.put(ConsumerConfig.GROUP_ID_CONFIG, 
                   "full_load_group"); 
        config.put( 
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
            StringDeserializer.class); 
        config.put( 
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
            StringDeserializer.class); 
  
        //config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        config.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 45*60000);
        
        return new DefaultKafkaConsumerFactory<>(config); 
    } 
	
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> 
      kafkaListenerContainerFactory() {
   
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
          new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
    
    @Bean
    public KafkaTemplate<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
          ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          bootstrapAddress);
        configProps.put(
          ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
          StringSerializer.class);
        configProps.put(
          ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
          StringSerializer.class);
        configProps.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, EpcEsPartitioner.class);
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(configProps));
    }
}
