package br.com.drpaz.voting.associate.model.dto

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.NotBlank

data class AssociateCreate(
        @field:NotBlank
        @field:CPF(message = "Invalid Cpf!")
        @field:ApiModelProperty(name = "cpf", example = "019.862.510-39", value = "Associate Cpf")
        val cpf: String = ""
)