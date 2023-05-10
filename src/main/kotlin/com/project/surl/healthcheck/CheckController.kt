package com.project.surl.healthcheck

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckController {

    @GetMapping("/check")
    fun healthcheck(): String {
        return "OK"
    }
}
