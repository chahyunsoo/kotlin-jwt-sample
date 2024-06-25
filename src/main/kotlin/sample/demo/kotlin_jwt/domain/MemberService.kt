package sample.demo.kotlin_jwt.domain

import sample.demo.kotlin_jwt.api.dto.request.MemberRequest

interface MemberService {
    fun signUp(memberRequest: MemberRequest): String


}