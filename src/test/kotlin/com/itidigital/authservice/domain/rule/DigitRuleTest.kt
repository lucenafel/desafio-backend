package com.itidigital.authservice.domain.rule

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class DigitRuleTest {

    @Test
    fun `requires at least one digit`() {
        val rule = DigitRule()

        val violation = rule.validate("abcdef")

        assertNotNull(violation)
        assertEquals(ValidationType.DIGIT, violation.type)
        assertEquals("Password must contain at least one digit", violation.message)
    }

    @Test
    fun `accepts passwords containing digits`() {
        val rule = DigitRule()

        assertNull(rule.validate("abc1def"))
    }
}
