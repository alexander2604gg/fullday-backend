package com.alexander.fullday.config.aws;

import com.alexander.fullday.config.properties.SesConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

@Configuration
@RequiredArgsConstructor
public class SesConfig {

    private final SesConfigProperties props;

    @Bean
    public SesClient sesClient() {
        return SesClient.builder()
                .region(Region.of(props.getRegion()))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        props.getAccessKey(),
                                        props.getSecretKey()
                                )
                        )
                )
                .build();
    }
}
