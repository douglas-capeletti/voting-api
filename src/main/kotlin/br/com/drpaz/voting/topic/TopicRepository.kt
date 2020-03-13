package br.com.drpaz.voting.topic

import br.com.drpaz.voting.topic.model.entity.Topic
import org.springframework.data.repository.PagingAndSortingRepository

interface TopicRepository : PagingAndSortingRepository<Topic, String> {

    fun findAllByName(name: String): List<Topic>

}
