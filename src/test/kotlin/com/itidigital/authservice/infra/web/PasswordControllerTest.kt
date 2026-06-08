package com.itidigital.authservice.infra.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.itidigital.authservice.infra.config.PasswordConfig
import com.itidigital.authservice.infra.web.dto.PasswordValidationRequest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(PasswordController::class)
@Import(PasswordConfig::class)
class PasswordControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private var objectMapper: ObjectMapper = ObjectMapper()

    companion object {
        @JvmStatic
        fun testCases() = listOf(
            // test cases do repositório do desafio
            Arguments.of("", false),
            Arguments.of("aa", false),
            Arguments.of("ab", false),
            Arguments.of("AAAbbbCc", false),
            Arguments.of("AbTp9!foo", false),
            Arguments.of("AbTp9!foA", false),
            Arguments.of("AbTp9 fok", false),
            Arguments.of("AbTp9!fok", true),

            // Outros testes relevantes
            Arguments.of("☠\uFE0FAbTp9!fok", true),
            Arguments.of("º»¼½¾¿ÀÁÂÃ", false),
            Arguments.of("º»¼½¾¿ÀÁÂÃ!2", true)
        )
    }

    @ParameterizedTest
    @MethodSource("testCases")
    fun `password validation cases`(password: String, result: Boolean) {
        mockMvc.post("/api/v1/password/validate") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                PasswordValidationRequest(password = password)
            )
        }
            .andExpect {
                status { isOk() }
                jsonPath("$.valid") { value(result) }
            }
    }
}