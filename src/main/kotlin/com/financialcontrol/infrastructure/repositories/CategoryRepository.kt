package com.financialcontrol.infrastructure.repositories

import com.financialcontrol.infrastructure.entities.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CategoryRepository: JpaRepository<CategoryEntity, UUID> {
}