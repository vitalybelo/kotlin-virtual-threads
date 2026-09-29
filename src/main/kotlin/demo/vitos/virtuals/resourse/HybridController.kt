package demo.vitos.virtuals.resourse

import demo.vitos.virtuals.service.IntegrationService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/virtual-threads")
class HybridController(
    private val integrationService: IntegrationService
) {
    private val logger = LoggerFactory.getLogger(HybridController::class.java)

    @GetMapping("/disabled/demo")
    fun getHybridData(): CompletableFuture<String> {

        val threadName = Thread.currentThread().toString()
        logger.info("Tomcat принял запрос в потоке: $threadName")

        // Контроллер мгновенно возвращает "обещание" результата и освобождает поток
        return integrationService.fetchExternalData()
    }
}