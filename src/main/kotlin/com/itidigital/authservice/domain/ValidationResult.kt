package com.itidigital.authservice.domain

data class ValidationResult(
    val valid: Boolean,
    val violations: List<Violation>
) {
    companion object {
        fun valid(): ValidationResult = ValidationResult(true, emptyList())
        fun invalid(violations: List<Violation>): ValidationResult = ValidationResult(false, violations)
    }
}