package com.alirio;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"classpath:features"},
        plugin = {"json:target/cucumber.json","pretty", "html:target/cucumber","timeline:target/timeline"},
        glue = {"com.alirio"},
        tags = "@Automation"
        )
public class RunnerIT {

}

