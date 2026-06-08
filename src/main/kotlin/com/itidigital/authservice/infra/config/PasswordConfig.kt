package com.itidigital.authservice.infra.config

import com.itidigital.authservice.domain.PasswordValidator
import com.itidigital.authservice.domain.rule.BlankCharacterRule
import com.itidigital.authservice.domain.rule.DigitRule
import com.itidigital.authservice.domain.rule.LowercaseRule
import com.itidigital.authservice.domain.rule.MinimumLengthRule
import com.itidigital.authservice.domain.rule.SpecialCharacterRule
import com.itidigital.authservice.domain.rule.UniqueCharacterRule
import com.itidigital.authservice.domain.rule.UppercaseRule
import com.itidigital.authservice.domain.rule.ValidationRule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PasswordConfig {

    companion object {
        private const val MINIMUM_LENGTH = 9L
        private const val SPECIAL_CHARACTERS = "!@#$%^&*()-+"
    }

    @Bean fun blankCharacterRule() = BlankCharacterRule()
    @Bean fun digitRule() = DigitRule()
    @Bean fun lowercaseRule() = LowercaseRule()
    @Bean fun minimumLengthRule() = MinimumLengthRule(MINIMUM_LENGTH)
    @Bean fun specialCharacterRule() = SpecialCharacterRule(SPECIAL_CHARACTERS.toHashSet())
    @Bean fun uniqueCharacterRule() = UniqueCharacterRule()
    @Bean fun uppercaseRule() = UppercaseRule()
    @Bean fun passwordValidator(rules: List<ValidationRule>) = PasswordValidator(rules)
}