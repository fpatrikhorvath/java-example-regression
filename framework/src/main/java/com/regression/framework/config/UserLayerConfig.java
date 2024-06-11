package com.regression.framework.config;

import org.springframework.context.annotation.Scope;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Scope(SCOPE_CUCUMBER_GLUE)
public class UserLayerConfig extends RestConfig {

    private final int port;

    public UserLayerConfig(final String protocol,
                           final String ip,
                           final int port) {
        super(protocol, ip);
        this.port = port;
    }

    public String getUrl() {
        return protocol + "://" + ip + ":" + port;
    }
}
