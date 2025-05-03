package dev.fastcampus.springmvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class SpringMvcApplication

fun main(args: Array<String>) {
    runApplication<SpringMvcApplication>(*args)
}
