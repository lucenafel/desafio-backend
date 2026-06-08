package com.itidigital.authservice.domain.rule

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class MinimumLengthRuleTest {

    @Test
    fun `rejects short passwords`() {
        val rule = MinimumLengthRule(minLength = 9)

        val violation = rule.validate("Abc123")

        assertNotNull(violation)
        assertEquals(ValidationType.MINIMUM_LENGTH, violation.type)
        assertEquals("Password must be at least 9 characters long", violation.message)
    }

    @Test
    fun `accepts passwords with enough characters`() {
        val rule = MinimumLengthRule(minLength = 9)

        assertNull(rule.validate("Abc123!xy"))
    }
}
