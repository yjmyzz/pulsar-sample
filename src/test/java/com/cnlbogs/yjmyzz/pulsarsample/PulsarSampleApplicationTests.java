package com.cnlbogs.yjmyzz.pulsarsample;

import org.apache.pulsar.client.api.PulsarClientException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PulsarSampleApplicationTests {

    @Autowired
    private SampleProducer producer;

    @Test
    public void contextLoads() throws PulsarClientException {
        producer.sendMessage(new Product("华为手机", new BigDecimal(5000), 1L, new Date()));
    }

}
