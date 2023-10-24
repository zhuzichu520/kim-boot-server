package com.chuzi.kim

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KimBootServerApplication

fun main(args: Array<String>) {
    runApplication<KimBootServerApplication>(*args)
}
