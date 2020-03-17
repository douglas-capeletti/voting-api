package br.com.drpaz.voting.topic.model.entity

import br.com.drpaz.voting.session.model.entity.Session
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity(name = "topic")
data class Topic(
        @field:Id
        @field:GeneratedValue(generator = "UUID")
        @field:GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        var id: String? = null,

        @field:Column(name = "name")
        var name: String? = null,

        @field:Column(name = "description")
        var description: String? = null,

        @field:OneToMany(mappedBy = "topic")
        var sessions: List<Session>? = null
)