package com.cnlbogs.yjmyzz.pulsarsample;

import org.apache.pulsar.client.api.*;
import org.apache.pulsar.client.impl.schema.JSONSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.KeyStore;
import java.util.concurrent.TimeUnit;

@Component
public class SampleConsumer implements InitializingBean {

    Logger logger = LoggerFactory.getLogger(SampleProducer.class);

    @Autowired
    private PulsarClient client;

    private Consumer<Product> consumer;

    public void onMessage() throws PulsarClientException {
        while (true) {
            // Wait for a message
            Message msg = consumer.receive();

            try {
                // Do something with the message
                System.out.printf("Message received: %s", msg.getValue());

                // Acknowledge the message so that it can be deleted by the message broker
                consumer.acknowledge(msg);
            } catch (Exception e) {
                // Message failed to process, redeliver later
//                consumermer.(msg);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            consumer = client.newConsumer(JSONSchema.of(Product.class))
                    .topic("product-info")
                    .subscriptionName("product-consumer")
                    .ackTimeout(10, TimeUnit.SECONDS)
                    .subscriptionType(SubscriptionType.Exclusive)
                    .subscribe();
            onMessage();
        } catch (PulsarClientException e) {
            logger.error("SampleProducer init error!", e);
        }
    }
}
