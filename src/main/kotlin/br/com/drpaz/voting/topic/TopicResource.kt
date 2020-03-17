package br.com.drpaz.voting.topic

import br.com.drpaz.voting.topic.model.dto.TopicCreate
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/topics")
class TopicResource constructor(private val facade: TopicFacade) {

    @PostMapping("/topic")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Register an new topic")
    @ApiResponses(ApiResponse(code = 201, message = "Resource successfully created!"))
    fun create(@Valid create: TopicCreate) = facade.create(create)


    @GetMapping("/topic/name/{name}")
    @ApiOperation("Get by topic name")
    @ApiResponses(ApiResponse(code = 200, message = "Resource successfully founded!"))
    fun getByName(@PathVariable name: String) = facade.getByName(name)

}