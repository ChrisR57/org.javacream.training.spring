package org.javacream.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="org.javacream")
@EntityScan(basePackages="org.javacream")
public class ApplicationConfiguration {

}
