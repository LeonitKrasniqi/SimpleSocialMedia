package cacttus.simplesocialmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimpleSocialMedia {

    public static void main(String[] args) {
        SpringApplication.run(SimpleSocialMedia.class, args);
    }

}
