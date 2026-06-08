package com.itidigital.authservice.domain.rule

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class UniqueCharacterRuleTest {

    @Test
    fun `rejects repeated characters`() {
        val rule = UniqueCharacterRule()

        val violation = rule.validate("Abc123!!")

        assertNotNull(violation)
        assertEquals(ValidationType.UNIQUE_CHARACTER, violation.type)
        assertEquals("Password must contain unique characters", violation.message)
    }

    @Test
    fun `accepts passwords with only unique characters`() {
        val rule = UniqueCharacterRule()

        assertNull(rule.validate("Abc123!"))
    }
}
