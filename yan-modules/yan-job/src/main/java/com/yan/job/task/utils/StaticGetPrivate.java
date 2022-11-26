package com.yan.job.task.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author yan
 **/
@Configuration
public class StaticGetPrivate {

    private static StaticGetPrivate staticGetPrivate;

    @Resource
    private RestTemplate restTemplate;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @PostConstruct
    public void init() {
        staticGetPrivate = this;
        staticGetPrivate.restTemplate = this.restTemplate;
        staticGetPrivate.emailFrom = this.emailFrom;
    }

    public static RestTemplate getResTemplate() {
        return staticGetPrivate.restTemplate;
    }

    public static String getEmailFrom() {
        return staticGetPrivate.emailFrom;
    }
}
