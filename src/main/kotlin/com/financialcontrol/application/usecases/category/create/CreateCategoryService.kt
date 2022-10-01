package com.financialcontrol.application.usecases.category.create

import arrow.core.Either
import arrow.core.toOption
import com.financialcontrol.application.resources.CategoryDTO
import com.financialcontrol.application.resources.CreateCategoryDTO
import com.financialcontrol.domain.adapters.CategoryAdapter
import com.financialcontrol.infrastructure.converters.CategoryConverter
import com.financialcontrol.infrastructure.repositories.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CreateCategoryService(private val categoryAdapter: CategoryAdapter) {

    fun execute(request: CreateCategoryDTO) =
        kotlin
            .runCatching {
                categoryAdapter.save(CreateCategoryDTO.to(request)).let {
                    CategoryDTO.to(it)
                }
            }
            .onFailure { throw Exception(it.localizedMessage) }
            .onSuccess { it }
            .getOrThrow()
}
