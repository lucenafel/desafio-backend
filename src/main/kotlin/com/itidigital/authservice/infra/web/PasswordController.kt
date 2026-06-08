package com.itidigital.authservice.infra.web

import com.itidigital.authservice.domain.PasswordValidator
import com.itidigital.authservice.infra.web.dto.PasswordValidationRequest
import com.itidigital.authservice.infra.web.dto.PasswordValidationResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/password")
class PasswordController(
    private val passwordValidator: PasswordValidator
) {

    @PostMapping("/validate")
    fun validatePassword(@RequestBody request: PasswordValidationRequest): PasswordValidationResponse =
        PasswordValidationResponse(passwordValidator.validate(request.password).valid)
}