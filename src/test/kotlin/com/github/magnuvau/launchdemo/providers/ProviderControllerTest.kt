package com.github.magnuvau.launchdemo.providers

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class ProviderControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `Controller is delayed by parameter`() {
        val configuredDelay = 2000;
        var responseDelay = System.currentTimeMillis()
        mockMvc.get("/endpoint?delay=${configuredDelay}").andReturn()
        responseDelay = System.currentTimeMillis() - responseDelay

        assertTrue(responseDelay > configuredDelay)
    }
}