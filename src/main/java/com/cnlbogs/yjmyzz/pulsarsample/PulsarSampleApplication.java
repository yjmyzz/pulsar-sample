package com.cnlbogs.yjmyzz.pulsarsample;

import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class PulsarSampleApplication {

    public static void main(String[] args) throws PulsarClientException {

        ConfigurableApplicationContext context = SpringApplication.run(PulsarSampleApplication.class, args);

        SampleProducer producer = context.getBean(SampleProducer.class);
        producer.sendMessage(new Product("华为手机", new BigDecimal(5000), 1L, new Date()));

    }

}
