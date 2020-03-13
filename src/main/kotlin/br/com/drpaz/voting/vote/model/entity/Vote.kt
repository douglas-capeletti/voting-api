package br.com.drpaz.voting.vote.model.entity

import br.com.drpaz.voting.associate.model.entity.Associate
import br.com.drpaz.voting.session.model.entity.Session
import javax.persistence.*

@Entity(name = "employee")
data class Vote(

        @field:EmbeddedId
        var id: VoteKey? = null,

        @field:ManyToOne
        @field:MapsId("id_session")
        var session: Session? = null,

        @field:ManyToOne
        @field:MapsId("id_associate")
        var associate: Associate? = null,

        @field:Column(name = "value")
        var value: Boolean? = null
)