package com.financialcontrol.category.findone

import com.fasterxml.jackson.module.kotlin.readValue
import com.financialcontrol.AbstractIntegrationTest
import com.financialcontrol.application.resources.CategoryDTO
import com.financialcontrol.domain.builders.CategoryBuilder
import com.financialcontrol.infrastructure.converters.CategoryConverter.of
import com.financialcontrol.infrastructure.repositories.CategoryRepository
import org.amshove.kluent.shouldBeEqualTo
import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@AutoConfigureMockMvc
class FindOneCategoryIntegrationTest: AbstractIntegrationTest() {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Test
    fun `#should return one category`(){
        categoryRepository.save(of(CategoryBuilder().build()))

        val id = UUID.randomUUID()

        val findOneRequest = MockMvcRequestBuilders.get("/category/$id").contentType(MediaType.APPLICATION_JSON)

        mockMvc.perform(findOneRequest)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect {
                val current = mapper.readValue<CategoryDTO>(
                    JSONObject(String(it.response.contentAsByteArray, charset("UTF-8"))).toString()
                )

                current.name.shouldBeEqualTo("category")
                current.enabled.shouldBeEqualTo(true)
            }

    }

    @Test
    fun`#should return status 404`(){
        val id = UUID.randomUUID()

        val findOneRequest = MockMvcRequestBuilders.get("/category/$id").contentType(MediaType.APPLICATION_JSON)

        mockMvc.perform(findOneRequest)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

}