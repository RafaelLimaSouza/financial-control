package com.financialcontrol.application.usecases.creditCard.create

import com.financialcontrol.UnitTest
import com.financialcontrol.application.resources.CreateCreditCardDTOBuilder
import com.financialcontrol.domain.adapters.CreditCardAdapter
import com.financialcontrol.domain.builders.CreditCardBuilder
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CreateCreditCardTest: UnitTest() {

    @MockK
    private lateinit var creditCardAdapter: CreditCardAdapter

    @InjectMockKs
    private lateinit var createCreditCardService: CreateCreditCardService

    @Nested
    inner class CreateCreditCardTest {

        @Test
        fun `should create a new credit card`() {
            val request = CreateCreditCardDTOBuilder().build()

            val expected = CreditCardBuilder().build()

            every { creditCardAdapter.save(any()) } returns expected.copy()

            createCreditCardService.execute(request).let {
                it.map { creditCardDTO ->
                    creditCardDTO.id shouldBeEqualTo expected.id
                }
            }
        }
    }
}