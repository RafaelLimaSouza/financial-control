package com.financialcontrol.application.usecases.category.create

import com.financialcontrol.UnitTest
import com.financialcontrol.application.resources.CreateCategoryDTOBuilder
import com.financialcontrol.domain.adapters.CategoryAdapter
import com.financialcontrol.domain.builders.CategoryBuilder
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class CreateCategoryServiceTest: UnitTest() {

    @MockK
    private lateinit var categoryAdapter: CategoryAdapter


    @InjectMockKs
    private lateinit var createCategoryService: CreateCategoryService

    @Test
    fun `#should create a new category`(){
        val request = CreateCategoryDTOBuilder().build()

        val expected = CategoryBuilder().build()

        every { categoryAdapter.save(any()) } returns expected.copy()

        createCategoryService.execute(request).also {
            it.map {categoryDTO -> categoryDTO.id shouldBeEqualTo expected.id }
        }
    }

}