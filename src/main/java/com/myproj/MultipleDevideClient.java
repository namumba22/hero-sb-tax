package com.myproj;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("${tax.multiple-devide-service-name}")
public interface MultipleDevideClient {

    @RequestMapping(value = "${tax.devide-and-get-url}", method = RequestMethod.GET)
    //TODO: replace with SPEL
    double devideAndGet(@PathVariable("amount") final double amount,
                        @PathVariable("amount2") final double amount2);
}


