package com.itidigital.authservice.domain.rule

import com.itidigital.authservice.domain.Violation

class SpecialCharacterRule(val charSet: Set<Char>) : ValidationRule {
    override val type: ValidationType = ValidationType.SPECIAL_CHARACTER
    override fun validate(password: String): Violation?{
        if (password.any { it in charSet }) return null

        return Violation(
            message = "Password must contain at least one special character",
            type = type
        )
    }
}
