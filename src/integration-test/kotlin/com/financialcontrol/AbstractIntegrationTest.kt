package com.financialcontrol

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.financialcontrol.utils.UUIDHelper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AbstractIntegrationTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    val mapper: ObjectMapper = ObjectMapper().copy().registerKotlinModule().registerModule(JavaTimeModule())

    @BeforeEach
    fun setup() {
        UUIDHelper.freezeRandomUUID()
    }

    @AfterEach
    fun tearDown() {
        UUIDHelper.unFreezeRandomUUID()
    }
}