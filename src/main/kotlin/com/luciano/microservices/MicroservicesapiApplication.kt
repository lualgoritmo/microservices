package com.luciano.microservices

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class MicroservicesapiApplication

fun main(args: Array<String>) {
	runApplication<MicroservicesapiApplication>(*args)
	println("Força subida")
}

