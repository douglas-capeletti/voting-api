package br.com.drpaz.voting.topic.model.dto

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank

data class TopicCreate(
        @field:NotBlank
        @field:ApiModelProperty(name = "name", example = "bats", value = "Topic name identifier")
        val name: String = "",
        @field:ApiModelProperty(name = "description", example = "Are batman a vampire?", value = "Topic description of the question")
        val description: String = ""
)
