package com.financialcontrol.utils

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import java.util.UUID

object UUIDHelper {

    fun freezeRandomUUID(uuid: UUID = UUID.randomUUID()){
        mockkStatic(UUID::class)
        every { UUID.randomUUID() } returns uuid
    }

    fun unFreezeRandomUUID() {
        unmockkStatic(UUID::class)
    }
}