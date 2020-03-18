package br.com.drpaz.voting.associate

import br.com.drpaz.voting.AbstractResourceTest
import br.com.drpaz.voting.associate.model.dto.AssociateCreate
import br.com.drpaz.voting.associate.model.dto.AssociateResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AssociateResourceTest : AbstractResourceTest() {

    private val api = "/api/v1/associates"

    @Test
    fun `Should return the generated id when create a new associate`() {
        //GIVEN
        val endpoint = api.plus("/associate")
        val body = AssociateCreate("16649699004")

        //WHEN
        val response = endpoint.doPostRequest(body).readResponseBody()

        //THEN
        assertThat(response).isNotNull()
    }

    @Test
    fun `Should return an error when trying to create an associate without cpf`() {
        //GIVEN
        val endpoint = api.plus("/associate")
        val body = AssociateCreate()

        //WHEN
        val response = endpoint.doPostRequest(body).readValidationException()

        //THEN
        assertThat(response.message[0]).isEqualTo("cpf: Invalid Cpf!")
    }

    @Test
    fun `Should return an error when trying to create an associate with an invalid cpf`() {
        //GIVEN
        val endpoint = api.plus("/associate")
        val body = AssociateCreate("INVALID")

        //WHEN
        val response = endpoint.doPostRequest(body).readValidationException()

        //THEN
        assertThat(response.message[0]).isEqualTo("cpf: Invalid Cpf!")
    }

    @Test
    fun `Should return the created associate when searching by id`() {

        //GIVEN
        var endpoint = api.plus("/associate")

        val body = AssociateCreate("16649699004")
        val id = endpoint.doPostRequest(body).readResponseBody()

        endpoint = endpoint.plus("/$id")

        //WHEN
        val response = endpoint.doGetRequest().readResponseBody(AssociateResponse::class.java)

        //THEN
        assertThat(response.id).isEqualTo(id)
        assertThat(response.cpf).isEqualTo(body.cpf)
    }

    @Test
    fun `Should return an error when searching for non-existent id`() {

        //GIVEN
        val nonExistingId = "NON-EXISTING"
        val endpoint = api.plus("/associate/$nonExistingId")

        //WHEN
        val response = endpoint.doGetRequest().readDefaultException()

        //THEN
        assertThat(response.reason).isEqualTo("Associate not found")
    }

    @Test
    fun `Should return the created associate when searching by cpf`() {

        //GIVEN
        var endpoint = api.plus("/associate")

        val body = AssociateCreate("16649699004")
        val id = endpoint.doPostRequest(body).readResponseBody()

        endpoint = endpoint.plus("/cpf/${body.cpf}")

        //WHEN
        val response = endpoint.doGetRequest().readResponseBody(Array<AssociateResponse>::class.java)

        //THEN
        assertThat(response[0].id).isEqualTo(id)
        assertThat(response[0].cpf).isEqualTo(body.cpf)
    }

    @Test
    fun `Should return an empty array when searching for non-existent cpf`() {

        //GIVEN
        val nonExistingCpf = "123123123123"
        val endpoint = api.plus("/associate/cpf/$nonExistingCpf")

        //WHEN
        val response = endpoint.doGetRequest().readResponseBody(Array<AssociateResponse>::class.java)

        //THEN
        assertThat(response).isEmpty()
    }

    @Test
    fun `Should return an error when searching for an invalid cpf`() {

        //GIVEN
        val invalidCpf = "INVALID-CPF"
        val endpoint = api.plus("/associate/cpf/$invalidCpf")

        //WHEN
        val response = endpoint.doGetRequest().readDefaultException()

        //THEN
        assertThat(response.reason).isEqualTo("Invalid Cpf!")
    }


}