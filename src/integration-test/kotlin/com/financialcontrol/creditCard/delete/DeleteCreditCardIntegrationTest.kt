package com.financialcontrol.creditCard.delete

import com.financialcontrol.AbstractIntegrationTest
import com.financialcontrol.application.resources.CreateCreditCardDTO
import com.financialcontrol.infrastructure.converters.CreditCardConverter.of
import com.financialcontrol.infrastructure.repositories.CreditCardRepository
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.UUID

@AutoConfigureMockMvc
class DeleteCreditCardIntegrationTest: AbstractIntegrationTest() {

    @Autowired
    lateinit var creditCardRepository: CreditCardRepository

    @Nested
    inner class DeleteCreditCardIntegrationTest {

        @Test
        fun `should successfully delete a credit card`(){
            val data = CreateCreditCardDTO(
                number = 1,
                flag = "VISA"
            )
            creditCardRepository.save(of(CreateCreditCardDTO.to(data)))

            val id = UUID.randomUUID()

            val request = MockMvcRequestBuilders.delete("/credit-card/$id")
                .contentType(MediaType.APPLICATION_JSON)

            mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent)
        }

        @Test
        fun`#should return status 404 when a credit card not exists`() {
            val id = "e92cac2f-5b99-427a-9e69-b0f69aac2df2"

            val deleteRequest = MockMvcRequestBuilders.delete("/credit-card/$id")
                .contentType(MediaType.APPLICATION_JSON)

            mockMvc.perform(deleteRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound)

        }
    }
}