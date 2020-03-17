package br.com.drpaz.voting.vote.model.entity

import br.com.drpaz.voting.associate.model.entity.Associate
import br.com.drpaz.voting.session.model.entity.Session
import javax.persistence.*

@Entity(name = "vote")
data class Vote(
        @field:EmbeddedId
        var id: VoteKey? = null,

        @field:ManyToOne
        @field:MapsId("session_id")
        var session: Session? = null,

        @field:ManyToOne
        @field:MapsId("associate_id")
        var associate: Associate? = null,

        @field:Column(name = "value")
        var value: Boolean? = null
)