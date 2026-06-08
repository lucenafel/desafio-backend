package com.itidigital.authservice.domain.rule

import com.itidigital.authservice.domain.Violation

class LowercaseRule : ValidationRule {
    override val type: ValidationType = ValidationType.LOWERCASE
    override fun validate(password: String) =
        if(password.any(Char::isLowerCase)) null
        else Violation(
            message = "Password must contain at least one lowercase letter",
            type = type
        )
}