package com.financialcontrol.infrastructure.adapters

import com.financialcontrol.application.resources.CreateCategoryDTO
import com.financialcontrol.domain.adapters.CategoryAdapter
import com.financialcontrol.domain.models.Category
import com.financialcontrol.infrastructure.converters.CategoryConverter
import com.financialcontrol.infrastructure.repositories.CategoryRepository
import org.springframework.stereotype.Component

@Component
class CategoryAdapterImpl(private val categoryRepository: CategoryRepository): CategoryAdapter {

    override fun save(category: Category) = CategoryConverter.of(
        categoryRepository.save(CategoryConverter.of(category))
    )
}