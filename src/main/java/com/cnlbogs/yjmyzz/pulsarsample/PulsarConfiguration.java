package com.cnlbogs.yjmyzz.pulsarsample;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PulsarConfiguration {

    Logger logger = LoggerFactory.getLogger(PulsarConfiguration.class);

    @Bean("pulsarClient")
    public PulsarClient pulsarClient() {
        try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl("pulsar://localhost:6650")
                    .build();
            return client;
        } catch (PulsarClientException e) {
            logger.error("pulsarClient init error:", e);
        }
        return null;
    }
}
