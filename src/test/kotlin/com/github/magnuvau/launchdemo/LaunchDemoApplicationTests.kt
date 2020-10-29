package com.github.magnuvau.launchdemo

import kotlinx.coroutines.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LaunchDemoApplicationTests {

	@Test
	fun `Synchronous consumers stack latency`() {
		var timer = System.currentTimeMillis()

		ConsumerController().synchronous(1000)
		ConsumerController().synchronous(2000)
		ConsumerController().synchronous(3000)

		timer = System.currentTimeMillis() - timer

		assertTrue(timer > 1000 + 2000 + 3000)
	}

	@Test
	fun `Asynchronous consumers run in parallell`() {
		var timer = System.currentTimeMillis()

		runBlocking {
			async { ConsumerController().asynchronous(1000) }
			async { ConsumerController().asynchronous(2000) }
			async { ConsumerController().asynchronous(3000) }
		}

		timer = System.currentTimeMillis() - timer

		println("Total latency: ${timer} ms")

		assertTrue(timer in 3000..4000)
	}
}
