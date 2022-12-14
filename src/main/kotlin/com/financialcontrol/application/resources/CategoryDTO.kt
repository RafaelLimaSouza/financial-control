package com.financialcontrol.application.resources

import com.financialcontrol.domain.models.Category
import java.util.UUID

data class CategoryDTO(
    val id: UUID,
    val name: String,
    val enabled: Boolean? = true,
){
    companion object {
        fun of(category: Category) = CategoryDTO(
            id = category.id,
            name = category.name,
            enabled = category.enabled,
        )
    }
}
