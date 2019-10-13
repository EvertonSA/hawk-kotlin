package `in`.arakaki.hawk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HawkApplication

fun main(args: Array<String>) {
	runApplication<HawkApplication>(*args)
}
