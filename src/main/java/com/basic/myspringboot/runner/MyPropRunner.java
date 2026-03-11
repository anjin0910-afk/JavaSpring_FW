package com.basic.myspringboot.runner;

import com.basic.myspringboot.config.MyEnvironment;
import com.basic.myspringboot.property.MyPropProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MyPropRunner implements ApplicationRunner {

    @Value("${myprop.username}")
    private String username;

    @Value("${myprop.port}")
    private int port;

    private final MyPropProperties myPropProperties;

    // Optional: Inject MyEnvironment if it's available.
    // If running in a profile where MyEnvironment is defined (like prod or test),
    // it will be injected.
    private final MyEnvironment myEnvironment;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("=========================");
        log.info("Environment Mode: {}", myEnvironment.getMode());
        log.debug("Using @Value - Username: {}, Port: {}", username, port);
        log.info("Using Properties class - Username: {}, Port: {}", myPropProperties.getUsername(),
                myPropProperties.getPort());
        log.info("=========================");
    }
}
