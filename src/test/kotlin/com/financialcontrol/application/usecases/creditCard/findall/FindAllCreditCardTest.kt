package com.financialcontrol.application.usecases.creditCard.findall

import com.financialcontrol.UnitTest
import com.financialcontrol.domain.adapters.CreditCardAdapter
import com.financialcontrol.domain.builders.CreditCardBuilder
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class FindAllCreditCardTest: UnitTest() {

    @MockK
    private lateinit var creditCardAdapter: CreditCardAdapter

    @InjectMockKs
    private lateinit var findAllCreditCardService: FindAllCreditCardService

    @Nested
    inner class FindAllCreditCardTest {

        @Test
        fun `should return a creditCard list`() {
            val expected = CreditCardBuilder().build()

            every { creditCardAdapter.findAll() } returns listOf(expected.copy())

            findAllCreditCardService.execute().map {
                it.first().id shouldBeEqualTo expected.id
            }
        }

        @Test
        fun `should return a empty list`() {
            every { creditCardAdapter.findAll() } returns emptyList()

            findAllCreditCardService.execute().map {
                it.size shouldBeEqualTo 0
            }
        }
    }
}