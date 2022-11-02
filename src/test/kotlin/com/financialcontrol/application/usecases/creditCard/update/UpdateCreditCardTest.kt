package com.financialcontrol.application.usecases.creditCard.update

import com.financialcontrol.UnitTest
import com.financialcontrol.application.resources.CreateCreditCardDTOBuilder
import com.financialcontrol.application.usecases.creditCard.update.UpdateCreditCardService
import com.financialcontrol.domain.adapters.CreditCardAdapter
import com.financialcontrol.domain.builders.CreditCardBuilder
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*

class UpdateCreditCardTest: UnitTest() {

    @MockK
    private lateinit var creditCardAdapter: CreditCardAdapter

    @InjectMockKs
    private lateinit var updateCreditCardService: UpdateCreditCardService

    @Nested
    inner class UpdateCreditCardTest {

        @Test
        fun `should successfully update a credit card`(){
            val request = CreateCreditCardDTOBuilder().build().copy(
                flag = "MASTERCARD"
            )

            val expected = CreditCardBuilder().build().copy(
                flag = "MASTERCARD"
            )

            val id = UUID.randomUUID()

            every { creditCardAdapter.save(any()) } returns expected.copy()

            updateCreditCardService.execute(id, request).map {
                it.id shouldBeEqualTo expected.id
                it.number shouldBeEqualTo expected.number
                it.flag shouldBeEqualTo expected.flag
                it.enabled shouldBeEqualTo expected.enabled
            }
        }
    }
}