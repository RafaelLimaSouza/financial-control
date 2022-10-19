package com.financialcontrol.application.usecases.category.findall

import com.financialcontrol.UnitTest
import com.financialcontrol.domain.adapters.CategoryAdapter
import com.financialcontrol.domain.enums.TypeEnum
import com.financialcontrol.domain.resources.CategoryBuilder
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

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
        val expected = CategoryBuilder().build()

        every { categoryAdapter.findAll() } returns emptyList()

        findAllCategoryService.execute().map {
            it.size shouldBeEqualTo 0
        }
    }

    @Test
    fun `should return a category by param`(){
        val expected = CategoryBuilder().build()

        every { categoryAdapter.findAll() } returns listOf(expected)

        findAllCategoryService.execute(TypeEnum.EXPENSE.name).map {
            it.first().id shouldBeEqualTo expected.id
        }
    }

}