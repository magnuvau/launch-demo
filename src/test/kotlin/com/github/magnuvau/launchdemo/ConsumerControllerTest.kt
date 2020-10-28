package com.github.magnuvau.launchdemo

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ConsumerControllerTest {

    @Test
    fun `Synchronous consumer test`() {
        var responseTimer = System.currentTimeMillis()
        ConsumerController().synchronous(2000)
        responseTimer = System.currentTimeMillis() - responseTimer

        assertTrue(responseTimer > 2000)
    }

    @Test
    fun `Asynchronous consumer test`() {
        var responseTimer = System.currentTimeMillis()
        runBlocking { ConsumerController().asynchronous(2000) }
        responseTimer = System.currentTimeMillis() - responseTimer

        assertTrue(responseTimer > 2000)
    }
}