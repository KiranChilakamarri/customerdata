package com.testcompany.app.customerdata.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("com.testcompany.app.customerdata")
@ImportResource("classpath:/META-INF/spring/spring-root-context.xml")
public class SpringApplicationContext {

    // can define any custom spring beans here

}
