package demo.vitos.virtuals.config

import org.apache.coyote.ProtocolHandler
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.tomcat.TomcatProtocolHandlerCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.AsyncTaskExecutor
import org.springframework.core.task.support.TaskExecutorAdapter
import org.springframework.scheduling.annotation.EnableAsync
import java.util.concurrent.Executors


/**
 *
 */
@EnableAsync
@Configuration
@ConditionalOnProperty(value = ["spring.threads.virtual.enabled"], havingValue = "true")
class ThreadConfig {

    @Bean
    fun applicationTaskExecutor(): AsyncTaskExecutor {
        return TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor())
    }

    @Bean
    fun protocolHandlerVirtualThreadExecutorCustomizer(): TomcatProtocolHandlerCustomizer<*> {
        return TomcatProtocolHandlerCustomizer { protocolHandler: ProtocolHandler? ->
            protocolHandler!!.executor = Executors.newVirtualThreadPerTaskExecutor()
        }
    }
}