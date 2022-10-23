package com.financialcontrol.infrastructure.adapters

import arrow.core.*
import com.financialcontrol.crosscutting.extensions.toOption
import com.financialcontrol.domain.adapters.CategoryAdapter
import com.financialcontrol.domain.enums.TypeEnum
import com.financialcontrol.domain.models.Category
import com.financialcontrol.infrastructure.converters.CategoryConverter
import com.financialcontrol.infrastructure.entities.CategoryEntity
import com.financialcontrol.infrastructure.repositories.CategoryRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CategoryAdapterImpl(private val categoryRepository: CategoryRepository): CategoryAdapter {

    override fun save(category: Category) = CategoryConverter.of(
        categoryRepository.save(CategoryConverter.of(category))
    )

    override fun findAll(): List<Category> =
        categoryRepository.findAll().map {
            CategoryConverter.of(it)
        }
    override fun findById(id: UUID): Option<Category> =
        categoryRepository.findById(id).map { CategoryConverter.of(it) }.toOption()

    override fun findByType(type: TypeEnum): List<Category> =
        categoryRepository.findByType(type).map { CategoryConverter.of(it) }

    override fun delete(category: CategoryEntity) {
        categoryRepository.delete(category)
    }
}