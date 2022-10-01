package com.financialcontrol.application.usecases.category.findall

import com.financialcontrol.application.resources.CategoryDTO
import com.financialcontrol.domain.adapters.CategoryAdapter
import org.springframework.stereotype.Service

@Service
class FindAllCategoryService(private val categoryAdapter: CategoryAdapter) {

    fun execute() =
        kotlin.runCatching {
            categoryAdapter.findAll().map {
                CategoryDTO.to(it)
            }
        }
            .onFailure { throw Exception(it.localizedMessage) }
            .onSuccess { it }
            .getOrThrow()
}