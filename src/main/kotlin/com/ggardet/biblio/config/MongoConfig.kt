package com.ggardet.biblio.config

import com.mongodb.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["com.ggardet.biblio.repository", "com.ggardet.biblio.security.repository"])
class MongoConfig(private val environment: Environment) : AbstractMongoConfiguration() {

    override fun getDatabaseName(): String {
        return environment.getProperty("spring.data.mongodb.database").orEmpty()
    }

    override fun mongoClient(): MongoClient {
        return MongoClient(environment.getProperty("spring.data.mongodb.host")!!, Integer.parseInt(environment.getProperty("spring.data.mongodb.port")!!))
    }

    @Bean
    @Throws(Exception::class)
    override fun mappingMongoConverter(): MappingMongoConverter {
        val mappingMongoConverter = super.mappingMongoConverter()
        mappingMongoConverter.setTypeMapper(null)

        return mappingMongoConverter
    }

}