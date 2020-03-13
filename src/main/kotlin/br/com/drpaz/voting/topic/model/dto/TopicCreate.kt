package br.com.drpaz.voting.topic.model.dto

import javax.validation.constraints.NotBlank

data class TopicCreate(
        @field:NotBlank
        val name: String = "",
        val description: String = ""
)
