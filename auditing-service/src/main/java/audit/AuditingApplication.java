package audit;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AuditingApplication {


    public static void main(String[] args) {
        SpringApplication.run(AuditingApplication.class, args);


    }

}