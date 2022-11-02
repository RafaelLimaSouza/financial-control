package com.financialcontrol.application.resources

class CreateCategoryDTOBuilder {

    var name: String = "category"
    var enabled: Boolean = true

    fun build() = CreateCategoryDTO(
        name = name,
        enabled = enabled,
    )
}