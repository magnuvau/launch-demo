package com.github.magnuvau.launchdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LaunchDemoApplication

fun main(args: Array<String>) {
	runApplication<LaunchDemoApplication>(*args)
}
