package com.financialcontrol.application.usecases.category.findone

import arrow.core.Either
import arrow.core.getOrElse
import arrow.core.right
import com.financialcontrol.application.resources.CategoryDTO
import com.financialcontrol.crosscutting.exceptions.BusinessException
import com.financialcontrol.crosscutting.exceptions.NotFoundException
import com.financialcontrol.domain.adapters.CategoryAdapter
import org.springframework.stereotype.Service
import java.util.UUID
import javax.persistence.EntityNotFoundException

@Service
class FindOneCategoryService(private val categoryAdapter: CategoryAdapter) {

    fun execute(id: UUID): Either<Throwable, CategoryDTO> =
        Either.catch {
            kotlin.runCatching {
                categoryAdapter.findById(id)
                    .map { CategoryDTO.of(it) }
                    .getOrElse { throw NotFoundException.of("Category", id) }
            }
            .onFailure { throw it }
            .onSuccess { it.right() }
            .getOrThrow()
        }
}