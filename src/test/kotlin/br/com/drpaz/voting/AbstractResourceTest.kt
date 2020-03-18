package br.com.drpaz.voting

import br.com.drpaz.voting.commom.exception.ValidationException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
abstract class AbstractResourceTest {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    private var jsonMapper: ObjectMapper = ObjectMapper()
            .apply { registerModule(KotlinModule()) }
            .apply { registerModule(JavaTimeModule()) }
            .apply { disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) }

    @Throws(Exception::class)
    protected fun String.doPostRequest(body: Any): ResultActions {
        return mockMvc
                .perform(MockMvcRequestBuilders.post(this)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(body)))
                .andDo(print())
    }

    @Throws(Exception::class)
    protected fun String.doGetRequest(): ResultActions {
        return mockMvc
                .perform(get(this))
                .andDo(print())
    }

    @Throws(Exception::class)
    protected fun <T> ResultActions.readResponseBody(clazz: Class<T>): T {
        return jsonMapper.readValue(this.andReturn().response.contentAsString, clazz)
    }

    @Throws(Exception::class)
    protected fun ResultActions.readValidationException(): ValidationException {
        return jsonMapper.readValue(this.andReturn().response.contentAsString, ValidationException::class.java)
    }

    @Throws(Exception::class)
    protected fun ResultActions.readDefaultException(): ResponseStatusException {
        return (this.andReturn().resolvedException ?: Exception()) as ResponseStatusException
    }

    protected fun ResultActions.readResponseBody(): String {
        return this.andReturn().response.contentAsString
    }
}