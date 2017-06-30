package com.myproj;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("${tax.rounder-service-name-service-name}")
public interface RounderClient {

    //TODO: replace with SPEL
    @RequestMapping(value = "${tax.round-url}", method = RequestMethod.GET)
    double round(@PathVariable("amount") final double amount);
}
