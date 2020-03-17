package br.com.drpaz.voting.session

import br.com.drpaz.voting.session.model.entity.Session
import br.com.drpaz.voting.topic.model.entity.Topic
import org.springframework.data.repository.PagingAndSortingRepository

interface SessionRepository : PagingAndSortingRepository<Session, String> {

    fun findAllByTopic(topic: Topic): List<Session>
}
