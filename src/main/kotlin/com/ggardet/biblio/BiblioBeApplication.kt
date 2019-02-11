package com.ggardet.biblio

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration
@ComponentScan
open class BiblioBeApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<BiblioBeApplication>(*args)
        }
    }

}
