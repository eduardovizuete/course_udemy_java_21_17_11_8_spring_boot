package com.preowned.cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class CarsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarsApplication.class, args);

        // print all spring beans in the application context
        //ConfigurableApplicationContext appContext = SpringApplication.run(CarsApplication.class, args);
        //Arrays.stream(appContext.getBeanDefinitionNames()).forEach(System.out::println);
    }

}
