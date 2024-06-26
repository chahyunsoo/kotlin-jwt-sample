package sample.demo.kotlin_jwt.domain

interface MemberRepository {
    fun findByLoginId(loginId: String?): Member?
    fun singUp(member: Member): Member?
}