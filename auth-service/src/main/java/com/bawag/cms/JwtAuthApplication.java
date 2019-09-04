package com.bawag.cms;


import com.bawag.cms.auth.model.AuditorAwareImpl;
import com.bawag.cms.jwt.JwtSimpleFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@ComponentScan({"com.bawag.cms.auth","com.bawag.cms.jwt"})
@EntityScan("com.bawag.cms.auth.model")
@EnableJpaRepositories("com.bawag.cms.auth.repository")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class JwtAuthApplication {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(JwtAuthApplication.class, args);
    }

    @Bean
    public JwtSimpleFilter simpleFilter() {
        return new JwtSimpleFilter();
    }
}