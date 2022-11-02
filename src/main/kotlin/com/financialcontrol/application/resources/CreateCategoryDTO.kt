package com.financialcontrol.application.resources

import com.financialcontrol.domain.models.Category
import javax.validation.constraints.NotBlank

data class CreateCategoryDTO(
    @NotBlank
    val name: String,
    val enabled: Boolean? = true,
){
    companion object {
        fun to(request: CreateCategoryDTO): Category {
            return Category(
                name = request.name,
                enabled = request.enabled
            )
        }
    }
}
