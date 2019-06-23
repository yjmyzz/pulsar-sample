package com.cnlbogs.yjmyzz.pulsarsample.consumer;

import com.cnlbogs.yjmyzz.pulsarsample.config.PulsarConfiguration;
import com.cnlbogs.yjmyzz.pulsarsample.model.Product;
import com.cnlbogs.yjmyzz.pulsarsample.producer.SampleProducer;
import org.apache.pulsar.client.api.*;
import org.apache.pulsar.client.impl.schema.JSONSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author jimmy
 */
@Component
public class SampleConsumer implements InitializingBean {

    Logger logger = LoggerFactory.getLogger(SampleProducer.class);

    @Autowired
    private PulsarClient client;


    @Override
    public void afterPropertiesSet() {
        try {
            client.newConsumer(JSONSchema.of(Product.class))
                    .messageListener((MessageListener<Product>) (consumer, msg) -> {
                        try {
                            System.out.printf("Message received: %s", msg.getValue());
                            consumer.acknowledge(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    })
                    .topic(PulsarConfiguration.TOPIC_NAME)
                    .subscriptionName("product-consumer")
                    .ackTimeout(10, TimeUnit.SECONDS)
                    .subscriptionType(SubscriptionType.Exclusive)
                    .subscribe();

        } catch (PulsarClientException e) {
            logger.error("SampleProducer init error!", e);
        }
    }
}
