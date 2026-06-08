package com.itidigital.authservice.domain

import com.itidigital.authservice.domain.rule.ValidationRule
import com.itidigital.authservice.domain.rule.ValidationType
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PasswordValidatorTest {

    @Test
    fun `returns valid result when all rules pass`() {
        val digitRule = mockk<ValidationRule>()
        val uppercaseRule = mockk<ValidationRule>()

        every { digitRule.type } returns ValidationType.DIGIT
        every { digitRule.validate(any()) } returns null
        every { uppercaseRule.type } returns ValidationType.UPPERCASE
        every { uppercaseRule.validate(any()) } returns null

        val validator = PasswordValidator(
            rules = listOf(
                digitRule,
                uppercaseRule
            )
        )

        val result = validator.validate("Abc123")

        assertTrue(result.valid)
        assertEquals(emptyList(), result.violations)
        verify(exactly = 1) { digitRule.validate("Abc123") }
        verify(exactly = 1) { uppercaseRule.validate("Abc123") }
    }

    @Test
    fun `executes every rule even when previous ones fail`() {
        val minimumLengthRule = mockk<ValidationRule>()
        val uppercaseRule = mockk<ValidationRule>()
        val digitRule = mockk<ValidationRule>()

        every { minimumLengthRule.type } returns ValidationType.MINIMUM_LENGTH
        every { minimumLengthRule.validate(any()) } returns Violation(
            "first",
            ValidationType.MINIMUM_LENGTH
        )
        every { uppercaseRule.type } returns ValidationType.UPPERCASE
        every { uppercaseRule.validate(any()) } returns null
        every { digitRule.type } returns ValidationType.DIGIT
        every { digitRule.validate(any()) } returns Violation("third", ValidationType.DIGIT)

        val validator = PasswordValidator(
            rules = listOf(
                minimumLengthRule,
                uppercaseRule,
                digitRule
            )
        )

        val result = validator.validate("abc")

        assertFalse(result.valid)
        assertEquals(
            listOf(
                Violation("first", ValidationType.MINIMUM_LENGTH),
                Violation("third", ValidationType.DIGIT)
            ),
            result.violations
        )
        verify(exactly = 1) { minimumLengthRule.validate("abc") }
        verify(exactly = 1) { uppercaseRule.validate("abc") }
        verify(exactly = 1) { digitRule.validate("abc") }
    }
}
