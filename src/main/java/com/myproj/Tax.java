package com.myproj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dumin on HZ koga.
 */

@ComponentScan
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

    @Autowired
    private MultipleDevideClient multipleDevideService;

    @Autowired
    private RounderClient rounderClient;

    @RequestMapping(value = "/tax", method = RequestMethod.GET)
    public double getVaStatus() {
        return rounderClient.round(multipleDevideService.devideAndGet(20, 99.5));
    }

    @RequestMapping(value = "/tax/{amount2:.+}", method = RequestMethod.GET)
    public double getTaxFromAmount(@PathVariable final double amount2) {
        return rounderClient.round(multipleDevideService.devideAndGet(amount2, 99.5));
    }

}
