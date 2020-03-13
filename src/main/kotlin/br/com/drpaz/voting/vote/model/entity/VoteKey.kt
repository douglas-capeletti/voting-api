package br.com.drpaz.voting.vote.model.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class VoteKey : Serializable {

    @field:Column(name = "id_topic")
    var topicId: String? = null

    @field:Column(name = "id_associate")
    var associateId: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as VoteKey

        if (topicId != other.topicId) return false
        if (associateId != other.associateId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = topicId?.hashCode() ?: 0
        result = 31 * result + (associateId?.hashCode() ?: 0)
        return result
    }
}