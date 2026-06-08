package com.itidigital.authservice.domain.rule

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class UppercaseRuleTest {

    @Test
    fun `requires at least one uppercase letter`() {
        val rule = UppercaseRule()

        val violation = rule.validate("abc123")

        assertNotNull(violation)
        assertEquals(ValidationType.UPPERCASE, violation.type)
        assertEquals("Password must contain at least one uppercase letter", violation.message)
    }

    @Test
    fun `accepts passwords containing uppercase letters`() {
        val rule = UppercaseRule()

        assertNull(rule.validate("abC123"))
    }
}
