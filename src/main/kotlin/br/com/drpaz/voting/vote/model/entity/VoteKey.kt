package br.com.drpaz.voting.vote.model.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class VoteKey : Serializable {
    @field:Column(name = "session_id")
    var sessionId: String? = null

    @field:Column(name = "associate_id")
    var associateId: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as VoteKey

        if (sessionId != other.sessionId) return false
        if (associateId != other.associateId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sessionId?.hashCode() ?: 0
        result = 31 * result + (associateId?.hashCode() ?: 0)
        return result
    }
}