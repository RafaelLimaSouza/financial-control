package com.financialcontrol.infrastructure.converters

import com.financialcontrol.domain.models.Category
import com.financialcontrol.infrastructure.entities.CategoryEntity

object CategoryConverter {

    fun of(category: Category) = CategoryEntity(
        id = category.id,
        name = category.name,
        enabled = category.enabled?.let { it } ?: true,
        createdAt = category.createAt,
        updatedAt = category.updatedAt
    )

    fun of(category: CategoryEntity) = Category(
        id = category.id,
        name = category.name,
        enabled = category.enabled,
        createAt = category.createdAt,
        updatedAt = category.updatedAt
    )
}