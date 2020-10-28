package com.github.magnuvau.launchdemo.consumers

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import org.apache.http.ssl.SSLContexts
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.net.URL

class ConsumerController {

    private val endpoint = "http://localhost:8080/endpoint"
    private val httpClient = HttpClient(Apache)
    private val log = LoggerFactory.getLogger(ConsumerController::class.java)

    fun synchronous(delay: Long): String {
        var timer = System.currentTimeMillis()
        val response = runBlocking { httpClient.get<String>(URL("${endpoint}?delay=${delay}")) }
        timer = System.currentTimeMillis() - timer
        log.info("Consumer latency [SYNC]: ${timer} ms")
        return response
    }

    suspend fun asynchronous(delay: Long): String {
        var timer = System.currentTimeMillis()
        val response = httpClient.get<String>(URL("${endpoint}?delay=${delay}"))
        timer = System.currentTimeMillis() - timer
        log.info("Consumer latency [ASYNC]: ${timer} ms")
        return response
    }
}