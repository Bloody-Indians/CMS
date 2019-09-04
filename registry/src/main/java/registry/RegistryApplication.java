package registry;

import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication

public class RegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistryApplication.class, args);

       /* PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
        Applications applications = registry.getApplications();

        applications.getRegisteredApplications().forEach((registeredApplication) -> {
            registeredApplication.getInstances().forEach((instance) -> {
                System.out.println(instance.getAppName() + " (" + instance.getInstanceId() + ") : " );
            });
        });*/
}}