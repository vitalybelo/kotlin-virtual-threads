package demo.vitos.virtuals.service

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class IntegrationService {

    @Async("asyncVirtualExecutor")
    fun fetchExternalData(): CompletableFuture<String> {

        Thread.sleep(1000L)
        val thread = Thread.currentThread().toString()
        return CompletableFuture.completedFuture("DISABLED :: успешно выполнено :: в потоке = $thread")
    }
}