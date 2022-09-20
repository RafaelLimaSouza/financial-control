package com.financialcontrol.application.resources

import com.financialcontrol.domain.enums.TypeEnum
import com.financialcontrol.domain.models.Category

data class CreateCategoryDTO(
    val name: String,
    val enabled: Boolean? = true,
    val type: String
){
    companion object {
        fun to(request: CreateCategoryDTO): Category {
            return Category(
                name = request.name,
                enabled = request.enabled,
                type = TypeEnum.valueOf(request.type)
            )
        }
    }
}
