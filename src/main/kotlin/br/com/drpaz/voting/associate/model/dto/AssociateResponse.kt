package br.com.drpaz.voting.associate.model.dto

import io.swagger.annotations.ApiModelProperty

data class AssociateResponse(
        @field:ApiModelProperty(name = "id", example = "db55261a-9075-4116-b7b7-5e9fdffd79b4", value = "Generated identifier")
        val id: String,
        @field:ApiModelProperty(name = "cpf", example = "019.862.510-39", value = "Associate Cpf")
        val cpf: String
)
