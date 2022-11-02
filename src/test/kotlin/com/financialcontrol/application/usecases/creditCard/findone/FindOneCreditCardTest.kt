package com.financialcontrol.application.usecases.creditCard.findone

import arrow.core.toOption
import com.financialcontrol.UnitTest
import com.financialcontrol.domain.adapters.CreditCardAdapter
import com.financialcontrol.domain.builders.CreditCardBuilder
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.UUID

class FindOneCreditCardTest: UnitTest() {

    @MockK
    private lateinit var creditCardAdapter: CreditCardAdapter

    @InjectMockKs
    private lateinit var findOneCreditCardService: FindOneCreditCardService

    @Nested
    inner class FindONeCreditCardTest {

        @Test
        fun `should return a creditCard`(){
            val expected = CreditCardBuilder().build()

            val id = UUID.randomUUID()

            every { creditCardAdapter.findById(id) } returns expected.toOption()

            findOneCreditCardService.execute(id).also {
                it.map { creditCardDTO -> creditCardDTO.id shouldBeEqualTo id }
            }
        }

    }
}