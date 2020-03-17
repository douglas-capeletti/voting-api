package br.com.drpaz.voting.vote.model.dto

import br.com.drpaz.voting.vote.model.enumeration.ResultEnum
import io.swagger.annotations.ApiModelProperty

data class VoteCountResponse(
        @field:ApiModelProperty(name = "positive", example = "1", value = "Positive votes")
        val positive: Long,
        @field:ApiModelProperty(name = "negative", example = "1", value = "Negative votes")
        val negative: Long,
        @field:ApiModelProperty(name = "result", example = "DRAW", value = "Result of vote counting")
        val result: ResultEnum,
        @field:ApiModelProperty(name = "open", example = "true", value = "Session opened status")
        val open: Boolean
)
