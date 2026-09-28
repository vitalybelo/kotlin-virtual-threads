package demo.vitos.virtuals

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinVirtualThreadsApplication

fun main(args: Array<String>) {
    runApplication<KotlinVirtualThreadsApplication>(*args)
}
