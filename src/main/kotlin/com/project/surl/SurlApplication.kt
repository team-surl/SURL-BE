package com.project.surl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class SurlApplication

fun main(args: Array<String>) {
    runApplication<SurlApplication>(*args)
}
