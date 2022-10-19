package com.financialcontrol.domain.resources

import com.financialcontrol.domain.enums.TypeEnum
import com.financialcontrol.domain.models.Category
import java.time.LocalDateTime
import java.util.UUID

class CategoryBuilder {

    var id: UUID = UUID.randomUUID()

    var name: String = "category"

    var enabled: Boolean = true

    var type: TypeEnum = TypeEnum.EXPENSE

    var createAt: LocalDateTime = LocalDateTime.now()

    var updatedAt: LocalDateTime = LocalDateTime.now()

    fun build() = Category(
        id = id,
        name = name,
        enabled = enabled,
        type = type,
        createAt = createAt,
        updatedAt = updatedAt
    )
}