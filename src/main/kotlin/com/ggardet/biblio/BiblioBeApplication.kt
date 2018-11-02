package com.ggardet.biblio

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BiblioBeApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<BiblioBeApplication>(*args)
        }
    }

}
