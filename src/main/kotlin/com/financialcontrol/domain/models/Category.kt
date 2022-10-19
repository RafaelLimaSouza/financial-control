package com.financialcontrol.domain.models

import com.financialcontrol.domain.enums.TypeEnum
import java.time.LocalDateTime
import java.util.UUID

data class Category(

    val id: UUID = UUID.randomUUID(),

    val name: String,

    val enabled: Boolean? = true,

    val type: TypeEnum,

    val createAt: LocalDateTime = LocalDateTime.now(),

    val updatedAt: LocalDateTime? = null
){}

