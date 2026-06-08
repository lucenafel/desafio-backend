package com.itidigital.authservice.domain.rule

import com.itidigital.authservice.domain.Violation

class MinimumLengthRule(private val minLength: Long) : ValidationRule {
    override val type: ValidationType = ValidationType.MINIMUM_LENGTH
    override fun validate(password: String): Violation? =
        if (password.length < minLength) Violation(
            message = "Password must be at least $minLength characters long",
            type = type
        )
        else null
}