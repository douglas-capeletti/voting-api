package br.com.drpaz.voting.commom.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.sql.Timestamp
import java.time.Instant

@ControllerAdvice
class ResponseExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity
                .unprocessableEntity()
                .body(
                        ValidationException(
                                Timestamp.from(Instant.now()),
                                HttpStatus.BAD_REQUEST.value(),
                                "Validation failed for object",
                                ex.bindingResult.fieldErrors.map { "${it.field}: ${it.defaultMessage}" },
                                (request as ServletWebRequest).request.requestURI ?: ""
                        )
                )
    }
}

data class ValidationException(
        val timestamp: Timestamp,
        val status: Int,
        val error: String,
        val message: List<String>,
        val path: String
)