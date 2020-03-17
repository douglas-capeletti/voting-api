package br.com.drpaz.voting.config

import br.com.drpaz.voting.associate.AssociateService
import br.com.drpaz.voting.associate.model.dto.AssociateCreate
import br.com.drpaz.voting.session.SessionService
import br.com.drpaz.voting.session.model.dto.SessionCreate
import br.com.drpaz.voting.topic.TopicService
import br.com.drpaz.voting.topic.model.dto.TopicCreate
import br.com.drpaz.voting.vote.VoteService
import br.com.drpaz.voting.vote.model.dto.VoteCreate
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.time.Duration

@Profile("!prod")
@Configuration
class TestSetupConfig(
        private val associate: AssociateService,
        private val session: SessionService,
        private val topic: TopicService,
        private val vote: VoteService) : CommandLineRunner {

    override fun run(vararg args: String) {
        topic.create(TopicCreate("joker", "should I kill the Jason Todd?"))
        session.createByTopicName("joker", SessionCreate(Duration.parse("PT5S")))
        session.createByTopicName("joker", SessionCreate(Duration.parse("PT5M"))).also {
            vote.vote(it, VoteCreate(associate.create(AssociateCreate("019.862.510-39")), false))
            vote.vote(it, VoteCreate(associate.create(AssociateCreate("308.920.220-43")), true))
            vote.vote(it, VoteCreate(associate.create(AssociateCreate("198.650.290-22")), true))
        }
    }

}