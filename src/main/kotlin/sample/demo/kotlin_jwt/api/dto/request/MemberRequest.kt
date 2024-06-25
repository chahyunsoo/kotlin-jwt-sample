package sample.demo.kotlin_jwt.api.dto.request

import sample.demo.kotlin_jwt.domain.Member
import sample.demo.kotlin_jwt.storage.entity.Gender
import java.time.LocalDate

data class MemberRequest(
        val id: Long?,
        val loginId: String,
        val password: String,
        val nickname: String,
        val birthDate: LocalDate,
        val gender: String,
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
