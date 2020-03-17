package br.com.drpaz.voting.session.model.dto

import br.com.drpaz.voting.topic.model.dto.TopicResponse
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

data class SessionResponse(
        @field:ApiModelProperty(name = "id", example = "db55261a-9075-4116-b7b7-5e9fdffd79b4", value = "Generated identifier")
        val id: String,
        @field:ApiModelProperty(name = "topic")
        val topic: TopicResponse?,
        @field:ApiModelProperty(name = "expirationDate", example = "2020-02-02T12:12:12.666666", value = "Expiration date")
        val expirationDate: LocalDateTime?,
        @field:ApiModelProperty(name = "open", example = "true", value = "Session opened status")
        val open: Boolean
)
