package sample.demo.kotlin_jwt.domain

import org.springframework.stereotype.Service
import sample.demo.kotlin_jwt.api.dto.request.MemberRequest

@Service
class MemberServiceImpl(
        private val memberRepository: MemberRepository
) : MemberService {
    override fun signUp(memberRequest: MemberRequest): String {
        //Id 중복검사
        var member: Member? = memberRepository.findByLoginId(memberRequest.loginId)
        if (member != null) {
            return "이미 등록된 id 입니다."
        }
        memberRepository.singUp(memberRequest.toMember())
        return "회원가입이 완료되었습니다."
    }

}