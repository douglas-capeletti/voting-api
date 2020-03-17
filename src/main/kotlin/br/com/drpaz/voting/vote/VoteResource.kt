package br.com.drpaz.voting.vote

import br.com.drpaz.voting.vote.model.dto.VoteCreate
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/votes")
class VoteResource constructor(private val facade: VoteFacade) {

    @PostMapping("/vote/session/{sessionId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Register an new note")
    @ApiResponses(ApiResponse(code = 201, message = "Resource successfully created!"))
    fun vote(@PathVariable sessionId: String, @Valid @RequestBody create: VoteCreate) = facade.vote(sessionId, create)

    @GetMapping("/count/session/{sessionId}")
    @ApiOperation("Count session votes and show status")
    @ApiResponses(ApiResponse(code = 200, message = "Resource successfully founded!"))
    fun countSessionVotes(@PathVariable sessionId: String) = facade.countSessionVotes(sessionId)

}