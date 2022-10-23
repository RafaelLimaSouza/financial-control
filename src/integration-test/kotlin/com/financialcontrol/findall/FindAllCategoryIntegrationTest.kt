package com.financialcontrol.findall

import com.fasterxml.jackson.module.kotlin.readValue
import com.financialcontrol.AbstractIntegrationTest
import com.financialcontrol.application.resources.CategoryDTO
import com.financialcontrol.domain.builders.CategoryBuilder
import com.financialcontrol.infrastructure.converters.CategoryConverter.of
import com.financialcontrol.infrastructure.repositories.CategoryRepository
import org.amshove.kluent.shouldBeEqualTo
import org.json.JSONArray
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import kotlin.test.assertEquals

@AutoConfigureMockMvc
class FindAllCategoryIntegrationTest: AbstractIntegrationTest() {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Test
    fun `#should return a category list`() {
        categoryRepository.save(of(CategoryBuilder().build()))

        val findAllRequest = MockMvcRequestBuilders.get("/category").contentType(MediaType.APPLICATION_JSON)

        mockMvc.perform(findAllRequest)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect {
                val current = mapper.readValue<List<CategoryDTO>>(JSONArray(String(it.response.contentAsByteArray,
                charset("UTF-8"))).toString())

                current[0].name.shouldBeEqualTo("category")
                current[0].type.shouldBeEqualTo("EXPENSE")
                current[0].enabled.shouldBeEqualTo(true)
            }
    }

    @Test
    fun `#should return a categories empty list`() {
        val request = MockMvcRequestBuilders.get("/category").contentType(MediaType.APPLICATION_JSON)

        mockMvc.perform(request)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect {
                it.response.contentLength.shouldBeEqualTo(0)
            }
    }
}