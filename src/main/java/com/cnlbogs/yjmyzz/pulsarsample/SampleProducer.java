package com.cnlbogs.yjmyzz.pulsarsample;

import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.impl.schema.JSONSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleProducer implements InitializingBean {

    Logger logger = LoggerFactory.getLogger(SampleProducer.class);

    @Autowired
    private PulsarClient client;

    private Producer<Product> producer;


    public MessageId sendMessage(Product product) throws PulsarClientException {
        MessageId msgId = producer.send(product);
        logger.info("msgId:" + msgId);
        return msgId;
    }

    @Override
    public void afterPropertiesSet() {
        try {
            producer = client.newProducer(JSONSchema.of(Product.class))
                    .topic("product-info")
                    .create();
        } catch (PulsarClientException e) {
            logger.error("SampleProducer init error!", e);
        }
    }
}
