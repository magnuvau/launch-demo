package com.github.magnuvau.launchdemo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProviderController {

    @GetMapping("/endpoint")
    fun endpoint(@RequestParam delay: Long): String {
        Thread.sleep(delay)
        return "Done!"
    }
}