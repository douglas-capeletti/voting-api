package br.com.drpaz.voting.session.model.dto

import io.swagger.annotations.ApiModelProperty
import java.time.Duration

data class SessionCreate(
        @field:ApiModelProperty(name = "duration", example = "PT1H30M", value = "Session opened duration")
        val duration: Duration?
)
