package com.financialcontrol.application.resources

import com.financialcontrol.domain.enums.TypeEnum
import com.financialcontrol.domain.models.Category
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class CreateCategoryDTO(
    @NotBlank
    val name: String,
    val enabled: Boolean? = true,
    @NotBlank
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
