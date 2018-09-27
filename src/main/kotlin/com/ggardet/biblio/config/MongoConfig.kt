package com.ggardet.biblio.config

import com.mongodb.MongoClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["com.ggardet.biblio.repository"])
@PropertySource("classpath:mongo.properties")
class MongoConfig : AbstractMongoConfiguration() {

    @Autowired
    private val env: Environment? = null

    override fun getDatabaseName(): String {
        return env!!.getProperty("mongo.database").orEmpty()
    }

    override fun mongoClient(): MongoClient {
        return MongoClient(env!!.getProperty("mongo.host"), Integer.parseInt(env.getProperty("mongo.port")!!))
    }

}
