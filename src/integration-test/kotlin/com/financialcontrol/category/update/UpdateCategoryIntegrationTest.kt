package com.financialcontrol.category.update

import com.financialcontrol.AbstractIntegrationTest
import com.financialcontrol.application.resources.CreateCategoryDTO
import com.financialcontrol.domain.builders.CategoryBuilder
import com.financialcontrol.infrastructure.converters.CategoryConverter.of
import com.financialcontrol.infrastructure.repositories.CategoryRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
class UpdateCategoryIntegrationTest: AbstractIntegrationTest() {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Test
    fun`#should update a category`(){
        val categoryId = categoryRepository.saveAndFlush(of(CategoryBuilder().build())).id

        val data = CreateCategoryDTO(name = "new_category")

        val updateRequest = MockMvcRequestBuilders.put("/category/$categoryId")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(data))

        mockMvc.perform((updateRequest))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNoContent)
    }

}