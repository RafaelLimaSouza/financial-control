package com.financialcontrol.creditCard.create

import com.financialcontrol.AbstractIntegrationTest
import com.financialcontrol.application.resources.CreateCreditCardDTO
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
class CreateCreditCardIntegrationTest: AbstractIntegrationTest() {

    @Nested
    inner class CreateCreditCardIntegrationTest {

        @Test
        fun `should return status 201 to create a new credit card`() {
            val data = CreateCreditCardDTO(
                number = 1234567890123456,
                flag = "VISA"
            )

            val request = MockMvcRequestBuilders.post("/credit-card").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(data))

            mockMvc.perform(request)
                .andDo( MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated)
        }
    }
}