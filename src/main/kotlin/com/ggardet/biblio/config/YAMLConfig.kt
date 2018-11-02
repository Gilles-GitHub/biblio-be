package com.ggardet.biblio.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import javax.validation.constraints.NotNull

@Configuration
@ConfigurationProperties(prefix = "application")
class YAMLConfig {

    @NotNull lateinit var secret: String
    @NotNull lateinit var authorization: String
    @NotNull lateinit var access_token: String
    @NotNull lateinit var bearer: String

}