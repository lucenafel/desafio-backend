package com.itidigital.authservice.domain.rule

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class LowercaseRuleTest {

    @Test
    fun `requires at least one lowercase letter`() {
        val rule = LowercaseRule()

        val violation = rule.validate("ABC123")

        assertNotNull(violation)
        assertEquals(ValidationType.LOWERCASE, violation.type)
        assertEquals("Password must contain at least one lowercase letter", violation.message)
    }

    @Test
    fun `accepts passwords containing lowercase letters`() {
        val rule = LowercaseRule()

        assertNull(rule.validate("ABCd123"))
    }
}
