package demo.vitos.virtuals.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.support.TaskExecutorAdapter
import org.springframework.scheduling.annotation.EnableAsync
import java.util.concurrent.Executors
import java.util.concurrent.Executor

@EnableAsync
@Configuration
@ConditionalOnProperty(value = ["spring.threads.virtual.enabled"], havingValue = "false")
class AsyncConfig {

    @Bean(name = ["asyncVirtualExecutor"])
    fun virtualExecutor(): Executor {
        // пул, который создает новый виртуальный поток для каждой задачи, помеченной @Async
        return TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor())
    }
}