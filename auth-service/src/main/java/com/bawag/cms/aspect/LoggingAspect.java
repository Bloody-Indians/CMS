package com.bawag.cms.aspect;

import com.bawag.cms.jwt.security.service.JwtAuthenticationResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging execution of service and repository Spring components.
 *
 * @author Ramesh Fadatare
 */
@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @AfterReturning(value = "execution(* com.bawag.cms.jwt.security.controller.AuthenticationRestController.createAuthenticationToken(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.info("{} returned with value {}", joinPoint, result);
        ResponseEntity responseEntity = (ResponseEntity) result;
        JwtAuthenticationResponse jwtAuthenticationResponse = (JwtAuthenticationResponse) responseEntity.getBody();
        log.info("  responseEntity.getStatusCode(); {}", responseEntity.getStatusCode());
        log.info("  responseEntity.getBody(); {}", jwtAuthenticationResponse);

    }
}
