package com.itidigital.authservice.domain

import com.itidigital.authservice.domain.rule.ValidationType

data class Violation(
    val message: String,
    val type: ValidationType
)