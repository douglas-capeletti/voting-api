package br.com.drpaz.voting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VotingApp

fun main(args: Array<String>) {
	runApplication<VotingApp>(*args)
}
