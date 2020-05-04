package com.ggardet.biblio.config

import com.mongodb.MongoClientSettings
import com.mongodb.ServerAddress
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.connection.ClusterSettings
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.convert.CustomConversions
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.convert.DbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
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

    @Bean
    fun mappingMongoConverter(factory: MongoDbFactory?, context: MongoMappingContext?, beanFactory: BeanFactory): MappingMongoConverter? {
        val dbRefResolver: DbRefResolver = DefaultDbRefResolver(factory!!)
        val mappingConverter = MappingMongoConverter(dbRefResolver, context!!)
        // remove _class field from properties of a saved document
        mappingConverter.setTypeMapper(DefaultMongoTypeMapper(null))
        try {
            mappingConverter.setCustomConversions(beanFactory.getBean(CustomConversions::class.java))
        } catch (ex: NoSuchBeanDefinitionException) {
            println(ex)
        }
        return mappingConverter
    }

}