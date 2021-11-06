package com.sachin.rabbit.producer.autoconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author Pekon
 */
@Configuration
@ComponentScan(value = "com.sachin.rabbit.producer.broker")
public class RabbitProducerAutoConfiguration {



}
