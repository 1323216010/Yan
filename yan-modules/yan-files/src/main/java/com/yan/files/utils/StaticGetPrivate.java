package com.yan.files.utils;

import com.github.yitter.contract.IdGeneratorOptions;
import com.yan.common.core.utils.id.SnowFlake;
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

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private IdGeneratorOptions idGeneratorOptions;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${kkFile.address}")
    private String kkAddres;

    @PostConstruct
    public void init() {
        staticGetPrivate = this;
        staticGetPrivate.restTemplate = this.restTemplate;
        staticGetPrivate.snowFlake = this.snowFlake;
        staticGetPrivate.emailFrom = this.emailFrom;
        staticGetPrivate.kkAddres = this.kkAddres;
        staticGetPrivate.idGeneratorOptions = this.idGeneratorOptions;
    }

    public static RestTemplate getResTemplate() {
        return staticGetPrivate.restTemplate;
    }

    public static SnowFlake getSnowFlake() {
        return staticGetPrivate.snowFlake;
    }

    public static IdGeneratorOptions getIdGeneratorOptions() {
        return staticGetPrivate.idGeneratorOptions;
    }

    public static String getEmailFrom() {
        return staticGetPrivate.emailFrom;
    }

    public static String getKkAddres() {
        return staticGetPrivate.kkAddres;
    }
}
