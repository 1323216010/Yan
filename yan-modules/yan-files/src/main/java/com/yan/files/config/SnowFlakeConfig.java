package com.yan.files.config;

import com.github.yitter.contract.IdGeneratorOptions;
import com.yan.common.core.utils.id.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 装配雪花算法
 *
 * @author yan
 **/
@Configuration
public class SnowFlakeConfig {
    @Bean
    public SnowFlake snowFlake() {
        return new SnowFlake(2,3);
    }
    @Bean
    public IdGeneratorOptions idGeneratorOptions() {
        return new IdGeneratorOptions();
    }
}
