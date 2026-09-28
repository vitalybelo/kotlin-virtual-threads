package demo.vitos.virtuals.resourse

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/virtual-threads")
class VirtualThreadController {

    @GetMapping("/demo")
    fun doWithDelay(): ResponseEntity<String> {

        Thread.sleep(1000L)
        val thread = Thread.currentThread().toString()
        return ResponseEntity.ok("Success :: performed by thread = $thread")
    }

}