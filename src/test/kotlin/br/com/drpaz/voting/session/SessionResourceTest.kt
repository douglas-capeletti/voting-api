package br.com.drpaz.voting.session

import br.com.drpaz.voting.AbstractResourceTest
import br.com.drpaz.voting.session.model.dto.SessionCreate
import br.com.drpaz.voting.session.model.dto.SessionResponse
import br.com.drpaz.voting.topic.model.dto.TopicCreate
import br.com.drpaz.voting.topic.model.dto.TopicResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Duration

internal class SessionResourceTest : AbstractResourceTest() {

    private val api = "/api/v1/sessions"

    @Test
    fun `Should return the generated id when create a new session by topic id specifying duration`() {
        //GIVEN
        val topic = createDefaultTopic()
        val endpoint = api.plus("/session/topic/").plus(topic.id)
        val body = SessionCreate(Duration.parse("PT1H30M"))

        //WHEN
        val response = endpoint.doPostRequest(body).readResponseBody()

        //THEN
        assertThat(response).isNotNull()
    }

    @Test
    fun `Should return the generated id when create a new session by topic id not specifying duration`() {
        //GIVEN
        val topic = createDefaultTopic()
        val endpoint = api.plus("/session/topic/").plus(topic.id)
        val body = SessionCreate(Duration.parse("PT1H30M"))

        //WHEN
        val response = endpoint.doPostRequest(body).readResponseBody()

        //THEN
        assertThat(response).isNotNull()
    }

    @Test
    fun `Should return the generated id when create a new session by topic name`() {
        //GIVEN
        val topic = createDefaultTopic()
        val endpoint = api.plus("/session/topic/name/").plus(topic.name)
        val body = SessionCreate(null)

        //WHEN
        val response = endpoint.doPostRequest(body).readResponseBody()

        //THEN
        assertThat(response).isNotNull()
    }

    @Test
    fun `Should return the an error when trying to create a new session by topic name that are not unique`() {
        //GIVEN
        createDefaultTopic()
        val topic = createDefaultTopic()
        val endpoint = api.plus("/topic/name/").plus(topic.name)
        val body = SessionCreate(null)

        //WHEN
        val response = endpoint.doPostRequest(body).readDefaultException()

        //THEN
        assertThat(response.reason).isEqualTo("More than one Topic found")
    }

    @Test
    fun `Should return the created session when searching by id`() {
        //GIVEN
        val topic = createDefaultTopic()
        val sessionId = api.plus("/session/topic/").plus(topic.id).doPostRequest(SessionCreate(null)).readResponseBody()
        val endpoint = api.plus("/session/").plus(sessionId)

        //WHEN
        val response = endpoint.doGetRequest().readResponseBody(SessionResponse::class.java)

        //THEN
        assertThat(response.id).isEqualTo(sessionId)
        assertThat(response.topic?.name).isEqualTo(topic.name)
        assertThat(response.open).isTrue()
    }

    @Test
    fun `Should return the created session with the specified duration when searching by id`() {
        //GIVEN
        val topic = createDefaultTopic()
        val body = SessionCreate(Duration.parse("-PT30M"))
        val sessionId = api.plus("/session/topic/").plus(topic.id).doPostRequest(body).readResponseBody()
        val endpoint = api.plus("/session/").plus(sessionId)

        //WHEN
        val response = endpoint.doGetRequest().readResponseBody(SessionResponse::class.java)

        //THEN
        assertThat(response.id).isEqualTo(sessionId)
        assertThat(response.topic?.name).isEqualTo(topic.name)
        assertThat(response.open).isFalse()
    }

    @Test
    fun `Should return the created session when searching by topic name`() {
        //GIVEN
        val topic = createDefaultTopic()
        val sessionId = api.plus("/session/topic/").plus(topic.id).doPostRequest(SessionCreate(null)).readResponseBody()
        val endpoint = api.plus("/topic/name/").plus(topic.name)

        //WHEN
        val response = endpoint.doGetRequest().readResponseBody(Array<SessionResponse>::class.java)

        //THEN
        assertThat(response[0].id).isEqualTo(sessionId)
        assertThat(response[0].topic?.name).isEqualTo(topic.name)
        assertThat(response[0].open).isTrue()
    }

    fun createDefaultTopic() = ("/api/v1/topics/topic/").run {
        this.plus(this.doPostRequest(TopicCreate("Default name", "Default description")).readResponseBody()).doGetRequest().readResponseBody(TopicResponse::class.java)
    }

}