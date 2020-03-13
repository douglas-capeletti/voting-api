package br.com.drpaz.voting.associate.model.dto

import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.NotBlank

data class AssociateCreate(
        @field:CPF
        @field:NotBlank
        val cpf: String = ""
)