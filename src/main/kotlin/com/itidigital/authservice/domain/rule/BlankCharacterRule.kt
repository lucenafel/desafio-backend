package com.itidigital.authservice.domain.rule

import com.itidigital.authservice.domain.Violation

class BlankCharacterRule : ValidationRule {
    override val type: ValidationType = ValidationType.BLANK_CHARACTER
    override fun validate(password: String) =
        if(password.any(Char::isWhitespace)) Violation(
            message = "Password must not contain blank characters",
            type = type
        )
        else null
}