package br.com.drpaz.voting.session

import br.com.drpaz.voting.session.model.dto.SessionCreate
import br.com.drpaz.voting.session.model.dto.SessionResponse

interface SessionFacade {

    fun create(topicId: String, create: SessionCreate): String
    fun createByTopicName(topicName: String, create: SessionCreate): String
    fun getById(id: String): SessionResponse
    fun getByTopicName(topicName: String): List<SessionResponse>
}
