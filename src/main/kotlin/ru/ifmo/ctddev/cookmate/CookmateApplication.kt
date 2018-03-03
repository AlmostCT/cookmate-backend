package ru.ifmo.ctddev.cookmate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CookmateApplication

fun main(args: Array<String>) {
    runApplication<CookmateApplication>(*args)
}
