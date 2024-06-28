package sample.demo.kotlin_jwt.domain

class Member(
        val id: Long?,
        val loginId: String?,
        var password: String?,
        var nickname: String?,
        var birthDate: String?,
        var gender: String?,
        var email: String?
) {
}