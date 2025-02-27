package de.vanclausen.date4u;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
public class Date4uApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Date4uApplication.class, args);
//        for (String beanName : ctx.getBeanDefinitionNames()) {
//            System.out.println(beanName);
//        }
        FileSystem fileSystem = ctx.getBean(FileSystem.class);
//        System.out.printf("Free disk space: %d GB%n", DataSize.ofBytes(fileSystem.getFreeDiskSpace()).toGigabytes());
    }

}
