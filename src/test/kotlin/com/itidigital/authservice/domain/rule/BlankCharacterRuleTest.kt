package com.itidigital.authservice.domain.rule

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class BlankCharacterRuleTest {

    @Test
    fun `rejects whitespace`() {
        val rule = BlankCharacterRule()

        val violation = rule.validate("abc def")

        assertNotNull(violation)
        assertEquals(ValidationType.BLANK_CHARACTER, violation.type)
        assertEquals("Password must not contain blank characters", violation.message)
    }

    @Test
    fun `accepts password without whitespace`() {
        val rule = BlankCharacterRule()

        assertNull(rule.validate("abcdef"))
    }
}
