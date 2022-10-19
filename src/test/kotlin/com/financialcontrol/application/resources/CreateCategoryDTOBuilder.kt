package com.financialcontrol.application.resources

import com.financialcontrol.domain.enums.TypeEnum

class CreateCategoryDTOBuilder {

    var name: String = "category"
    var enabled: Boolean = true
    var type: String = TypeEnum.EXPENSE.name

    fun build() = CreateCategoryDTO(
        name = name,
        enabled = enabled,
        type = type
    )
}