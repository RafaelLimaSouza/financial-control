package com.financialcontrol.crosscutting.exceptions

import java.util.UUID

class NotFoundException(
    private val targetEntity: String,
    private val targetField: String,
    private val value: String
    ): BusinessException("$targetEntity with $targetField $value was not found"){

    companion object {
        fun of(entity: String, id: UUID) = NotFoundException(entity, "ID", id.toString())

        fun of(entity: String, id: String) = NotFoundException(entity, "ID", id)
    }

}