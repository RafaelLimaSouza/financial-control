package com.financialcontrol.domain.adapters

import arrow.core.Option
import com.financialcontrol.domain.models.Category
import com.financialcontrol.infrastructure.entities.CategoryEntity
import java.util.UUID

interface CategoryAdapter {
    fun save(category: Category): Category
    fun findAll(): List<Category>
    fun findById(id: UUID): Option<Category>
    fun delete(category: CategoryEntity)
}