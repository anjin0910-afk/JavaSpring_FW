package com.rookies5.MySpringbootLab.runner;

import com.rookies5.MySpringbootLab.property.MyPropProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyPropRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyPropRunner.class);

    @Value("${myprop.username}")
    private String username;

    @Value("${myprop.port}")
    private int port;

    @Autowired
    private MyPropProperties myPropProperties;

    @Autowired(required = false)
    private com.rookies5.MySpringbootLab.config.MyEnvironment myEnvironment;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("====================================");
        logger.debug("@Value username: {}", username);
        logger.debug("@Value port: {}", port);
        logger.debug("====================================");
        
        logger.info("MyPropProperties username: {}", myPropProperties.getUsername());
        logger.info("MyPropProperties port: {}", myPropProperties.getPort());
        logger.info("====================================");
        
        if (myEnvironment != null) {
            logger.info("Active Profile Mode: {}", myEnvironment.getMode());
        } else {
            logger.info("No Active Profile (MyEnvironment is null)");
        }
    }
}
