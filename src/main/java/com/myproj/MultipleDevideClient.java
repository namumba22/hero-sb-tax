package com.myproj;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("${tax.multiple-devide-service-name}")
public interface MultipleDevideClient {

    @RequestMapping(value = "${tax.devide-and-get-url}", method = RequestMethod.GET)
    //TODO: replace with SPEL
    @HystrixCommand(fallbackMethod = "defaultRound",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "9000")
    })
    double devideAndGet(@PathVariable("amount") final double amount,
                        @PathVariable("amount2") final double amount2);

    default double defaultRound(final double amount) {
        return 8888;
    }
}


