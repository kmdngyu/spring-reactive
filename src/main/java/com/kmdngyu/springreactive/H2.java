package com.kmdngyu.springreactive;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class H2 {
    private Server webServer;
    @EventListener(ContextRefreshedEvent.class)
    public void start() throws java.sql.SQLException {
        log.info("starting h2 console at port 8078");
        this.webServer = org.h2.tools.Server.createWebServer("-webPort", "8078", "-tcpAllowOthers").start();
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        log.info("stopping h2 console at port 8078");
        this.webServer.stop();
    }
}

