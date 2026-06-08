package com.itidigital.authservice.domain

import com.itidigital.authservice.domain.rule.ValidationRule
import org.slf4j.LoggerFactory

class PasswordValidator(
    private val rules: List<ValidationRule>
) {
    companion object {
        private val log = LoggerFactory.getLogger(PasswordValidator::class.java)
    }

    fun validate(password: String): ValidationResult {
        val violations = rules.mapNotNull { rule ->
            rule.validate(password).also { violation ->
                if (violation != null) log.debug("Rule: {} violated: {}", rule.type, violation.message)
            }
        }

        return if (violations.isEmpty()) ValidationResult.valid()
        else ValidationResult.invalid(violations)


    }
}