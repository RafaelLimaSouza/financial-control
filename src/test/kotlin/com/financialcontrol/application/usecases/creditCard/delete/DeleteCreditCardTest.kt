package com.financialcontrol.application.usecases.creditCard.delete

import com.financialcontrol.UnitTest
import com.financialcontrol.application.usecases.creditCard.delete.DeleteCreditCardService
import com.financialcontrol.domain.adapters.CreditCardAdapter
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.UUID

class DeleteCreditCardTest: UnitTest() {

    @MockK
    private lateinit var creditCardAdapter: CreditCardAdapter

    @InjectMockKs
    private lateinit var deleteCreditCardService: DeleteCreditCardService

    @Nested
    inner class DeleteCreditCardTest {
        @Test
        fun `should successfully delete a credit card`(){
            val id = UUID.randomUUID()

            every { creditCardAdapter.delete(any()) } returns Unit

            deleteCreditCardService.execute(id).map { it
                it shouldBeEqualTo Unit
            }

        }
    }
}