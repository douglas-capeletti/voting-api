package br.com.drpaz.voting.util.exception

import org.springframework.http.HttpStatus.*
import org.springframework.web.server.ResponseStatusException

class ResourceNotFoundException constructor(resource: String) : ResponseStatusException(NOT_FOUND, "$resource not found")
class MoreThanOneException constructor(resource: String) : ResponseStatusException(UNPROCESSABLE_ENTITY, "More than one $resource found")
class ExpiredSessionException : ResponseStatusException(UNPROCESSABLE_ENTITY, "This Session is now closed!")
class UncheckedSaveResponseException : ResponseStatusException(INTERNAL_SERVER_ERROR, "Internal error, please verify if the resource has been saved!")