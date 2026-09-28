package demo.vitos.virtuals

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis
import org.slf4j.LoggerFactory


fun main() {

    val counter = AtomicInteger()
    val client = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder(URI.create("http://localhost:8080/virtual-threads/demo")).build()
    val logger = LoggerFactory.getLogger("main()")

    val duration = measureTimeMillis {
        Executors.newVirtualThreadPerTaskExecutor().use { executor ->
            repeat(1000) {
                executor.submit {
                    try {
                        Thread.sleep(1L)
                        val response =
                            client.send(request, HttpResponse.BodyHandlers.ofString())
                        counter.incrementAndGet()
                        logger.info("Response from server : ${response.body()}")
                    } catch (ex: Exception) {
                        logger.error("Ошибка запроса: message = ${ex.message}, cause = ${ex.cause}")
                    }
                }
            }
        }
    }

    logger.info("Успешных запросов: ${counter.get()}")
    logger.info("Время выполнения: $duration мс")
}
