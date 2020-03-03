package com.ggardet.biblio.config

import com.mongodb.Block
import com.mongodb.MongoClientSettings
import com.mongodb.ServerAddress
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.connection.ClusterSettings
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@Configuration
@EnableMongoRepositories(basePackages = ["com.ggardet.biblio.repository"])
class MongoConfig(private val environment: Environment) : AbstractMongoClientConfiguration() {

    override fun getDatabaseName(): String {
        return environment.getProperty("spring.data.mongodb.database").orEmpty()
    }

    override fun mongoClient(): MongoClient {
        val host = environment.getProperty("spring.data.mongodb.host")!!
        val port = environment.getProperty("spring.data.mongodb.port")!!
        val serverAddress = ServerAddress(host, Integer.parseInt(port))
        val clusterSettings = ClusterSettings.builder().hosts(arrayListOf(serverAddress)).build()
        val mongoClientSettings = MongoClientSettings.builder().applyToClusterSettings { t -> t.applySettings(clusterSettings) }.build()
        return MongoClients.create(mongoClientSettings)
    }

    @Bean
    @Throws(Exception::class)
    override fun mappingMongoConverter(): MappingMongoConverter {
        val mappingMongoConverter = super.mappingMongoConverter()
        mappingMongoConverter.setTypeMapper(null)
        return mappingMongoConverter
    }

}