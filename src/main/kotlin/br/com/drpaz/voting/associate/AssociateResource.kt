package br.com.drpaz.voting.associate

import br.com.drpaz.voting.associate.model.dto.AssociateCreate
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/associates")
class AssociateResource constructor(private val service: AssociateService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Register an new associate")
    @ApiResponses(ApiResponse(code = 201, message = "Resource successfully created!"))
    fun create(@Valid create: AssociateCreate) = service.create(create)


    @GetMapping
    @ApiOperation("Find by associate cpf")
    @ApiResponses(ApiResponse(code = 200, message = "Resource successfully founded!"))
    fun findByCpf(@RequestParam cpf: String) = service.findByCPF(cpf)

}