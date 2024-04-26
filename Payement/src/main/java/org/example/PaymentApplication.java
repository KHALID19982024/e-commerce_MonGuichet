package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("org.example.Mapper") // Add this line
//        (
//        scanBasePackages = {
//                "org.example.payment",
//                "dev.nano.amqp"
//        }
//)
//@EnableEurekaClient
//@EnableFeignClients(
//        basePackages = "dev.nano.clients"
//)
//@PropertySources({
//        @PropertySource("classpath:amqp-${spring.profiles.active}.properties"),
//        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
//})
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }
}
