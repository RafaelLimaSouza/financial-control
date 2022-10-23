package com.financialcontrol.application.usecases.category.update

import arrow.core.toOption
import com.financialcontrol.UnitTest
import com.financialcontrol.application.resources.CreateCategoryDTOBuilder
import com.financialcontrol.domain.adapters.CategoryAdapter
import com.financialcontrol.domain.builders.CategoryBuilder
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.*

class UpdateCategoryServiceTest: UnitTest() {
    @MockK
    private lateinit var categoryAdapter: CategoryAdapter

    @InjectMockKs
    private lateinit var updateCategoryService: UpdateCategoryService

    @Test
    fun `should update a category`(){
        val request = CreateCategoryDTOBuilder().build().copy()

        val expected = CategoryBuilder().build()

        val id = UUID.randomUUID()

        every { categoryAdapter.findById(id) } returns expected.copy().toOption()
        every { categoryAdapter.save(any()) } returns expected

        updateCategoryService.execute(id, request).also {
            it.map { it.id shouldBeEqualTo expected.id }
        }
    }

}