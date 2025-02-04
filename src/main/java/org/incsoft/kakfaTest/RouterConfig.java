package org.incsoft.kakfaTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    KafkaMessageSender kafkaMessageSender;

    @Bean
    public RouterFunction<ServerResponse> sendKafkaMessge() {
        return RouterFunctions.route().GET("/sendMessage/{msg}", req -> kafkaMessageSender.sendMessage("Test1", req.pathVariable("msg")))
          .build();
    }
}