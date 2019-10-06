package com.ragavan.authentication.config.cache;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:oauth2-cache.xml"})
public class OAuth2CacheConfig {

}
