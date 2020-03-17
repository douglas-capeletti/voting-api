package br.com.drpaz.voting.topic.model.dto

import io.swagger.annotations.ApiModelProperty

data class TopicResponse(
        @field:ApiModelProperty(name = "id", example = "db55261a-9075-4116-b7b7-5e9fdffd79b4", value = "Generated identifier")
        val id: String,
        @field:ApiModelProperty(name = "name", example = "bats", value = "Topic name identifier")
        val name: String,
        @field:ApiModelProperty(name = "description", example = "Are batman a vampire?", value = "Topic description of the question")
        val description: String
)
