package com.financialcontrol.application.usecases.category.update

import arrow.core.Either
import arrow.core.getOrElse
import arrow.core.right
import com.financialcontrol.application.resources.CategoryDTO
import com.financialcontrol.application.resources.CreateCategoryDTO
import com.financialcontrol.domain.adapters.CategoryAdapter
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UpdateCategoryService(private val categoryAdapter: CategoryAdapter) {

    fun execute(id: UUID, request: CreateCategoryDTO): Either<Throwable, CategoryDTO> =
        Either.catch {
            kotlin
                .runCatching {
                    categoryAdapter.findById(id).map { entity ->
                        val newCategory = entity.copy(
                            id = id,
                            name = request.name,
                            enabled = request.enabled ?: entity.enabled,
                        )
                        categoryAdapter.save(newCategory).let {
                            CategoryDTO.of(it)
                        }
                    }.getOrElse { throw Exception("Not found") }
                }
                .onFailure { throw Exception(it.localizedMessage) }
                .onSuccess { it.right() }
                .getOrThrow()
        }
}
