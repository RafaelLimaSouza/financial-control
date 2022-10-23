package com.financialcontrol.application.usecases.category.create

import arrow.core.Either
import arrow.core.right
import com.financialcontrol.application.resources.CategoryDTO
import com.financialcontrol.application.resources.CreateCategoryDTO
import com.financialcontrol.domain.adapters.CategoryAdapter
import org.springframework.stereotype.Service

@Service
class CreateCategoryService(private val categoryAdapter: CategoryAdapter) {

    fun execute(request: CreateCategoryDTO): Either<Throwable, CategoryDTO> =
        Either.catch {
            kotlin
                .runCatching {
                    categoryAdapter.save(CreateCategoryDTO.to(request)).let {
                        CategoryDTO.of(it)
                    }
                }
                .onFailure { throw Exception(it.localizedMessage) }
                .onSuccess { it.right() }
                .getOrThrow()
        }

}
