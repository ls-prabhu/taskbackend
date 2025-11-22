package com.to.`do`

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class TodoApplication

fun main(args: Array<String>) {
	runApplication<TodoApplication>(*args)
}
