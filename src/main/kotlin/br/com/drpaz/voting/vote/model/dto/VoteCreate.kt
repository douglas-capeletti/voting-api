package br.com.drpaz.voting.vote.model.dto

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class VoteCreate(
        @field:NotBlank
        @field:ApiModelProperty(name = "associateId", example = "db55261a-9075-4116-b7b7-5e9fdffd79b4", value = "Associate generated identifier")
        val associateId: String = "",
        @field:NotNull
        @field:ApiModelProperty(name = "vote", example = "true", value = "Vote value")
        val vote: Boolean? = null
)
