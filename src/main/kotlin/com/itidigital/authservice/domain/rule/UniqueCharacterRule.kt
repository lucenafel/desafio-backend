package com.itidigital.authservice.domain.rule

import com.itidigital.authservice.domain.Violation

class UniqueCharacterRule: ValidationRule {
    override val type: ValidationType = ValidationType.UNIQUE_CHARACTER
    override fun validate(password: String): Violation? =
        if (password.toHashSet().size == password.length) null
        else Violation(
            message = "Password must contain unique characters",
            type = type
        )
}