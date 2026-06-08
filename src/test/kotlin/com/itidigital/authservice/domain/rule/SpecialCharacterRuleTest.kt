package com.itidigital.authservice.domain.rule

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class SpecialCharacterRuleTest {
    private val testSet = setOf('!')


    @Test
    fun `rejects passwords without allowed characters`() {
        val rule = SpecialCharacterRule(testSet)

        val violation = rule.validate("Abc123xy")

        assertNotNull(violation)
        assertEquals(ValidationType.SPECIAL_CHARACTER, violation.type)
        assertEquals("Password must contain at least one special character", violation.message)
    }

    @Test
    fun `accepts passwords with allowed characters`() {
        val rule = SpecialCharacterRule(testSet)

        assertNull(rule.validate("Abc123!"))
    }
}
