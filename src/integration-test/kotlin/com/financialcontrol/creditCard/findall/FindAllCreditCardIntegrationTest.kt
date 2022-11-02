package com.financialcontrol.creditCard.findall

import com.fasterxml.jackson.module.kotlin.readValue
import com.financialcontrol.AbstractIntegrationTest
import com.financialcontrol.application.resources.CreateCreditCardDTO
import com.financialcontrol.application.resources.CreditCardDTO
import com.financialcontrol.infrastructure.converters.CreditCardConverter.of
import com.financialcontrol.infrastructure.repositories.CreditCardRepository
import org.amshove.kluent.shouldBeEqualTo
import org.json.JSONArray
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
class FindAllCreditCardIntegrationTest: AbstractIntegrationTest() {

    @Autowired
    lateinit var creditCardRepository: CreditCardRepository

    @Nested
    inner class FindAllCreditCardIntegrationTest {


        @Test
        fun `should return a credit card list`(){
            val data = CreateCreditCardDTO(
                number = 2,
                flag = "VISA"
            )
            creditCardRepository.save(of(CreateCreditCardDTO.to(data)))

            val request = MockMvcRequestBuilders.get("/credit-card").contentType(MediaType.APPLICATION_JSON)

            mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect {
                    val current = mapper.readValue<List<CreditCardDTO>>(
                        JSONArray(String(it.response.contentAsByteArray,
                        charset("UTF-8"))).toString())

                    current[0].number.shouldBeEqualTo(1234567890123456)
                    current[0].flag.shouldBeEqualTo("VISA")
                }
        }

        @Test
        fun `should return a empty list`(){
            val request = MockMvcRequestBuilders.get("/credit-card").contentType(MediaType.APPLICATION_JSON)

            mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect {
                   it.response.contentLength.shouldBeEqualTo(0)
                }

        }

    }
}