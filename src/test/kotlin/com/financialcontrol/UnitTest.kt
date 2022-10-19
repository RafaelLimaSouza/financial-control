package com.financialcontrol

import com.financialcontrol.utils.UUIDHelper
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
open class UnitTest {

	@BeforeEach
	fun setup() {
		UUIDHelper.freezeRandomUUID()
	}

	@AfterEach
	fun tearDown() {
		UUIDHelper.unFreezeRandomUUID()
	}

}
