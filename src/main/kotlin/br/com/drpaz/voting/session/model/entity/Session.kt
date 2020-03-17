package br.com.drpaz.voting.session.model.entity

import br.com.drpaz.voting.topic.model.entity.Topic
import br.com.drpaz.voting.vote.model.entity.Vote
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "session")
data class Session(
        @field:Id
        @field:GeneratedValue(generator = "UUID")
        @field:GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        var id: String? = null,

        @field:JoinColumn(name = "topic_id")
        @field:ManyToOne(fetch = FetchType.LAZY)
        var topic: Topic? = null,

        @field:OneToMany(mappedBy = "session")
        var votes: List<Vote>? = null,

        @field:Column(name = "expiration_date")
        var expirationDate: LocalDateTime? = null,

        @field:Column(name = "created_date")
        var createdDate: LocalDateTime? = null
)