package com.financialcontrol.domain.builders

import com.financialcontrol.domain.models.Category
import java.time.LocalDateTime
import java.util.UUID

class CategoryBuilder {

    var id: UUID = UUID.randomUUID()

    var name: String = "category"

    var enabled: Boolean = true

    var createAt: LocalDateTime = LocalDateTime.now()

    var updatedAt: LocalDateTime = LocalDateTime.now()

    fun build() = Category(
        id = id,
        name = name,
        enabled = enabled,
        createAt = createAt,
        updatedAt = updatedAt
    )
}