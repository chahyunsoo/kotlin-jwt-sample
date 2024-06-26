package sample.demo.kotlin_jwt.api.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import sample.demo.kotlin_jwt.common.annotation.ValidEnum
import sample.demo.kotlin_jwt.domain.Member
import sample.demo.kotlin_jwt.storage.entity.Gender
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class MemberRequest(
        val id: Long?,

        @field:NotBlank
        val loginId: String?,

        @field:NotBlank
        @field:Pattern(
                regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{8,20}\$",
                message = "영문, 숫자, 특수문자를 포함한 8~20 자리로 입력해주세요."
        )
        val password: String?,

        @field:NotBlank
        val nickname: String?,

        @field:NotBlank
        @field:Pattern(
                regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$",
                message = " (YYYY-MM-DD) "
        )
        val birthDate: String?,

        @field:NotBlank
        @field:ValidEnum(
                enumClass = Gender::class,
                message = "MEN 이나 WOMEN 둘중에 하나의 값을 선택해주세요."
        )
        val gender: String,

        @field:NotBlank
        @field:Email
        val email: String,
) {
    fun toMember(): Member {
        return Member(
                id = null,
                loginId = loginId,
                password = password,
                nickname = nickname,
                birthDate = birthDate,
                gender = gender,
                email = email
        )
    }
}
