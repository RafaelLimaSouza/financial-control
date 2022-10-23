package com.financialcontrol.application.usecases.category.findone

import arrow.core.toOption
import com.financialcontrol.UnitTest
import com.financialcontrol.domain.adapters.CategoryAdapter
import com.financialcontrol.domain.builders.CategoryBuilder
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.*

class FindOneCategoryServiceTest: UnitTest() {

    @MockK
    private lateinit var categoryAdapter: CategoryAdapter

    @InjectMockKs
    private lateinit var findOneCategoryService: FindOneCategoryService

    @Test
    fun `should return one category when find by id`(){
        val expected = CategoryBuilder().build()

        val id = UUID.randomUUID()

        every { categoryAdapter.findById(id) } returns expected.copy().toOption()

        findOneCategoryService.execute(id).also {
            it.map { categoryDTO ->  categoryDTO.id shouldBeEqualTo expected.id}
        }
    }
}