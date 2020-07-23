package com.alirio;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "com.alirio")
@PropertySource("classpath:configuration.properties")
public class CucumberSpringContextConfiguration {

}
