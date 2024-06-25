package sample.demo.kotlin_jwt.domain

import java.time.LocalDate

class Member(
        val id: Long?,
        val loginId: String,
        var password: String,
        var nickname: String,
        var birthDate: LocalDate,
        var gender: String,
        var email: String
) {
}