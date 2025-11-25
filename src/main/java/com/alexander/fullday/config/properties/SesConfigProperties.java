package com.alexander.fullday.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aws")
@Data
public class SesConfigProperties {
    private String accessKey;
    private String secretKey;
    private String region;
}