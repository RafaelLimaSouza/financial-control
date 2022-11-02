package com.financialcontrol.category.create

import com.financialcontrol.AbstractIntegrationTest
import com.financialcontrol.application.resources.CreateCategoryDTO
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
class CreateCategoryIntegrationTest: AbstractIntegrationTest() {

    @Test
    fun `#should return status 201 to create a new category`() {
        val data = CreateCategoryDTO(name="category")
        val request = MockMvcRequestBuilders.post("/category").contentType(MediaType.APPLICATION_JSON).
            content(mapper.writeValueAsString(data))

        mockMvc.perform(request)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isCreated)
    }
}