package com.financialcontrol.creditCard.findone

import com.fasterxml.jackson.module.kotlin.readValue
import com.financialcontrol.AbstractIntegrationTest
import com.financialcontrol.application.resources.CreateCreditCardDTO
import com.financialcontrol.application.resources.CreditCardDTO
import com.financialcontrol.infrastructure.converters.CreditCardConverter
import com.financialcontrol.infrastructure.repositories.CreditCardRepository
import org.amshove.kluent.shouldBeEqualTo
import org.json.JSONObject
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
class FindOneCreditCardIntegrationTest: AbstractIntegrationTest() {

    @Autowired
    lateinit var creditCardRepository: CreditCardRepository

    @Nested
    inner class FindOneCreditCardIntegrationTest {

        @Test
        fun `should successfully return one credit card`(){
            val data = CreateCreditCardDTO(
                number = 3,
                flag = "VISA"
            )
            creditCardRepository.save(CreditCardConverter.of(CreateCreditCardDTO.to(data)))

            val id = UUID.randomUUID()
            val request = MockMvcRequestBuilders.get("/credit-card/$id")
                .contentType(MediaType.APPLICATION_JSON)

            mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect {
                    val current = mapper.readValue<CreditCardDTO>(
                        JSONObject(String(it.response.contentAsByteArray, charset("UTF-8"))).toString()
                    )

                    current.number.shouldBeEqualTo(3)
                    current.flag.shouldBeEqualTo("VISA")
                }

        }

        @Test
        fun `should return status 404 when a id not exists`(){
            val id = UUID.randomUUID()
            val request = MockMvcRequestBuilders.get("/credit-card/$id")
                .contentType(MediaType.APPLICATION_JSON)

            mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound)
        }
    }
}