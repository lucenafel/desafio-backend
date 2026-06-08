package com.itidigital.authservice.domain.rule

import com.itidigital.authservice.domain.Violation

class UppercaseRule : ValidationRule {
    override val type: ValidationType = ValidationType.UPPERCASE
    override fun validate(password: String): Violation? =
        if (password.any(Char::isUpperCase)) null
        else Violation(
            message = "Password must contain at least one uppercase letter",
            type = type
        )
}