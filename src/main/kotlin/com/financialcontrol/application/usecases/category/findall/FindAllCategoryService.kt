package com.financialcontrol.application.usecases.category.findall

import arrow.core.Either
import arrow.core.right
import com.financialcontrol.application.resources.CategoryDTO
import com.financialcontrol.domain.adapters.CategoryAdapter
import org.springframework.stereotype.Service

@Service
class FindAllCategoryService(private val categoryAdapter: CategoryAdapter) {

    fun execute(): Either<Throwable, List<CategoryDTO>> =
        Either.catch {
            kotlin.runCatching {
                categoryAdapter.findAll().map {
                    CategoryDTO.of(it)
                }
            }
            .onFailure { throw Exception(it.localizedMessage) }
            .onSuccess { it.right() }
            .getOrThrow()
        }
}