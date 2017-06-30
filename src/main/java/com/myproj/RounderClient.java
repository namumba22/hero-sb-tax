package com.myproj;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("${tax.rounder-service-name}")
public interface RounderClient {

    //TODO: replace with SPEL
    @RequestMapping(value = "${tax.round-url}", method = RequestMethod.GET)
//    @HystrixCommand(fallbackMethod = "defaultRound",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "9000")
//    })
    double round(@PathVariable("amount") final double amount);

    default double defaultRound(final double amount) {
        return 777;
    }


}
