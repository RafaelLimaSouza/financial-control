package com.financialcontrol.creditCard.update

import com.financialcontrol.AbstractIntegrationTest
import com.financialcontrol.application.resources.CreateCreditCardDTO
import com.financialcontrol.domain.builders.CreditCardBuilder
import com.financialcontrol.infrastructure.converters.CreditCardConverter
import com.financialcontrol.infrastructure.repositories.CreditCardRepository
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@AutoConfigureMockMvc
class UpdateCreditCardIntegrationTest: AbstractIntegrationTest() {

    @Autowired
    lateinit var creditCardRepository: CreditCardRepository

    @Nested
    inner class UpdateCreditCardIntegrationTest {

        @Test
        fun`should update a credit card`(){
            val data = CreateCreditCardDTO(
                number = 4,
                flag = "VISA"
            )

            val id = creditCardRepository.saveAndFlush(CreditCardConverter.of(CreateCreditCardDTO.to(data))).id

            val request = MockMvcRequestBuilders.put("/credit-card/$id")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(data.copy(
                    flag = "MASTERCARD"
                )))

            mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent)
        }

        @Test
        fun `should return status 404 when a id not exists`(){
            val id = UUID.fromString("54e07732-9edc-4ae4-8ff6-d2ed11720d20")
            val data = CreditCardBuilder().build()

            val request = MockMvcRequestBuilders.put("/credit-card/$id")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(data.copy(
                    flag = "MASTERCARD"
                )))

            mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound)
        }
    }
}