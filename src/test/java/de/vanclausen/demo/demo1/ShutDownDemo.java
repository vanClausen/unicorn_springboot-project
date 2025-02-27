package de.vanclausen.demo.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class ShutDownDemo {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ShutDownDemo.class);
        app.setDefaultProperties(Map.of("spring.pid.fail-on-write-error", "true"));
        app.addListeners(new ApplicationPidFileWriter());
        try (ConfigurableApplicationContext ctx = app.run(args)) {
            System.out.println("Press Enter to stop the application...");
            Logger log = LoggerFactory.getLogger(ShutDownDemo.class);
            log.info("{}", new ApplicationPid());
//            System.exit(SpringApplication.exit(ctx, () -> 2));
            new Scanner(System.in).nextLine();
        }
    }
}
