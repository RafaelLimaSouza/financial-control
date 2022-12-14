package com.financialcontrol.application.usecases.category.findall

import com.financialcontrol.UnitTest
import com.financialcontrol.application.usecases.category.findall.FindAllCategoryService
import com.financialcontrol.domain.adapters.CategoryAdapter
import com.financialcontrol.domain.builders.CategoryBuilder
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class FindAllCategoryServiceTest: UnitTest() {

    @MockK
    private lateinit var categoryAdapter: CategoryAdapter


    @InjectMockKs
    private lateinit var findAllCategoryService: FindAllCategoryService

    @Test
    fun `should return a category list`(){
        val expected = CategoryBuilder().build()

        every { categoryAdapter.findAll() } returns listOf(expected.copy())

        findAllCategoryService.execute().map {
            it.first().id shouldBeEqualTo expected.id
        }
    }

    @Test
    fun `should return a category a empty list`(){
         every { categoryAdapter.findAll() } returns emptyList()

        findAllCategoryService.execute().map {
            it.size shouldBeEqualTo 0
        }
    }
}