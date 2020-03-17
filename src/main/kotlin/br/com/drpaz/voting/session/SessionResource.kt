package br.com.drpaz.voting.session

import br.com.drpaz.voting.session.model.dto.SessionCreate
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/sessions")
class SessionResource constructor(private val facade: SessionFacade) {

    @PostMapping("/session/topic/{topicId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Register an new session using topic id")
    @ApiResponses(ApiResponse(code = 201, message = "Resource successfully created!"))
    fun create(@PathVariable topicId: String, @Valid @RequestBody create: SessionCreate) = facade.create(topicId, create)

    @PostMapping("/topic/name/{topicName}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Register an new session using topic name")
    @ApiResponses(
            ApiResponse(code = 201, message = "Resource successfully created!"),
            ApiResponse(code = 422, message = "Impossible to process data!"))
    fun createByTopicName(@PathVariable topicName: String, @Valid @RequestBody create: SessionCreate) = facade.createByTopicName(topicName, create)

    @GetMapping("/session/{id}")
    @ApiOperation("Get by id")
    @ApiResponses(ApiResponse(code = 200, message = "Resource successfully founded!"))
    fun getById(@PathVariable id: String) = facade.getById(id)

    @GetMapping("/topic/name/{topicName}")
    @ApiOperation("Get by id")
    @ApiResponses(ApiResponse(code = 200, message = "Resource successfully founded!"))
    fun getByTopicName(@PathVariable topicName: String) = facade.getByTopicName(topicName)
}
