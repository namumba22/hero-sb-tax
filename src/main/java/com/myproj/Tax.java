package com.myproj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dumin on HZ koga.
 */

@EnableAutoConfiguration
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.myproj"})
@PropertySource("classpath:tax.properties")
@RestController
public class Tax {

    public static void main(String... args) throws Exception {
        SpringApplication.run(Tax.class, args);
    }

    @RequestMapping(value = "/",  method = {RequestMethod.GET})
    public String help() {
        return "try https:// ... /tax";
    }


    @Autowired
    private MultipleDevideClient multipleDevideClient;

    @Autowired
    private RounderClient rounderClient;

    @RequestMapping(value = "/tax", method = RequestMethod.GET)
    public double getTax() {
        return rounderClient.round(multipleDevideClient.devideAndGet(20, 99.5));
//        return 12.11;
    }

    @RequestMapping(value = "/tax/{amount:.+}", method = RequestMethod.GET)
    public double getTaxByAmount(@PathVariable final double amount) {
        return rounderClient.round(multipleDevideClient.devideAndGet(amount, 20.5));
    }

}
