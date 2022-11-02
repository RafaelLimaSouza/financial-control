package com.financialcontrol.category.delete

import com.financialcontrol.AbstractIntegrationTest
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
import java.util.UUID

@AutoConfigureMockMvc
class DeleteCategoryIntegrationTest: AbstractIntegrationTest() {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Test
    fun`#should successfully delete a category`() {
        categoryRepository.save(of(CategoryBuilder().build()))

        val id = UUID.randomUUID()

        val deleteRequest = MockMvcRequestBuilders.delete("/category/$id").contentType(MediaType.APPLICATION_JSON)

        mockMvc.perform(deleteRequest)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNoContent)

    }

    @Test
    fun`#should return status 404 when a category not exists`() {
        val id = "e92cac2f-5b99-427a-9e69-b0f69aac2df2"

        val deleteRequest = MockMvcRequestBuilders.delete("/category/$id").contentType(MediaType.APPLICATION_JSON)

        mockMvc.perform(deleteRequest)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNotFound)

    }

}