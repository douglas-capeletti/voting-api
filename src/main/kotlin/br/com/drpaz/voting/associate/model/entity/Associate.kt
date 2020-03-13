package br.com.drpaz.voting.associate.model.entity

import br.com.drpaz.voting.vote.model.entity.Vote
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "associate")
data class Associate(
        @field:Id
        @field:GeneratedValue(generator = "UUID")
        @field:GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        var id: String? = null,

        @field:Column(name = "cpf")
        var cpf: String? = null,

        @field:OneToMany(mappedBy = "associate")
        var votes: List<Vote>? = null,

        @field:Column(name = "created_date")
        var createdDate: LocalDateTime? = null
)