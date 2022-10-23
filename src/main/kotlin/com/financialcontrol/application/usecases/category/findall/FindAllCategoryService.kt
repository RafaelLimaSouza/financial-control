package com.financialcontrol.application.usecases.category.findall

import arrow.core.Either
import arrow.core.right
import com.financialcontrol.application.resources.CategoryDTO
import com.financialcontrol.domain.adapters.CategoryAdapter
import com.financialcontrol.domain.enums.TypeEnum
import org.springframework.stereotype.Service

@Service
class FindAllCategoryService(private val categoryAdapter: CategoryAdapter) {

    fun execute(type: String? = null): Either<Throwable, List<CategoryDTO>> =
        Either.catch {
            kotlin.runCatching {
                type?.let {
                    categoryAdapter.findByType(TypeEnum.valueOf(it)).map { category ->
                        CategoryDTO.of(category)
                    }
                } ?:
                categoryAdapter.findAll().map {
                    CategoryDTO.of(it)
                }
            }
            .onFailure { throw Exception(it.localizedMessage) }
            .onSuccess { it.right() }
            .getOrThrow()
        }
}