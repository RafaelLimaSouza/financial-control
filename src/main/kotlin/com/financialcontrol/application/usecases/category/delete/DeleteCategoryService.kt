package com.financialcontrol.application.usecases.category.delete

import arrow.core.Either
import arrow.core.getOrElse
import arrow.core.right
import arrow.core.toOption
import com.financialcontrol.crosscutting.exceptions.NotFoundException
import com.financialcontrol.domain.adapters.CategoryAdapter
import com.financialcontrol.infrastructure.converters.CategoryConverter.of
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DeleteCategoryService(private val categoryAdapter: CategoryAdapter) {

    fun execute(id: UUID): Either<Throwable, Unit> =
        Either.catch {
            kotlin.runCatching {
                categoryAdapter.findById(id).map {
                    category -> categoryAdapter.delete(of(category))
                }.getOrElse { throw NotFoundException.of("Category", id) }
            }
            .onFailure { throw it }
            .onSuccess { it.right() }
            .getOrThrow()
        }
}