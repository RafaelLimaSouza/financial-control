package com.financialcontrol.domain.models

import java.time.LocalDateTime
import java.util.UUID

data class CreditCard(
    val id: UUID = UUID.randomUUID(),

    val number: Long,

    val flag: String,

    val enabled: Boolean? = true,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    val updatedAt: LocalDateTime? = null
){}
