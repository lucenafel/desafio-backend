package com.itidigital.authservice.domain.rule

import com.itidigital.authservice.domain.Violation

class DigitRule : ValidationRule {
    override val type: ValidationType = ValidationType.DIGIT
    override fun validate(password: String) =
        if (password.any(Char::isDigit)) null
        else Violation(
            message = "Password must contain at least one digit",
            type = type
        )
}