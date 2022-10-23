package com.financialcontrol.application.usecases.category.delete

import com.financialcontrol.UnitTest
import com.financialcontrol.domain.adapters.CategoryAdapter
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.*

class DeleteCategoryServiceTest: UnitTest() {

    @MockK
    private lateinit var categoryAdapter: CategoryAdapter

    @InjectMockKs
    private lateinit var deleteCategoryService: DeleteCategoryService

    @Test
    fun `should delete a category`(){
        val id = UUID.randomUUID()

        every { categoryAdapter.delete(any()) } returns Unit

        deleteCategoryService.execute(id).map { it
            it shouldBeEqualTo Unit
        }
    }
}