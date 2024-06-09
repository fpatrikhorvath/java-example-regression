package com.regression.framework.config;

import org.springframework.context.annotation.Scope;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Scope(SCOPE_CUCUMBER_GLUE)
public class ParabankConfig extends RestConfig{

    private final String path;

    public ParabankConfig(final String protocol,
                          final String ip,
                          final String path) {
        super(protocol, ip);
        this.path = path;
    }

    public String getUrl() {
        return protocol + "://" + ip + ":" + path;
    }
}
