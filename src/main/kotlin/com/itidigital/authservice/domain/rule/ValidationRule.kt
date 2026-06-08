package com.itidigital.authservice.domain.rule

import com.itidigital.authservice.domain.Violation

interface ValidationRule {
    val type: ValidationType
    fun validate(password: String): Violation?
}