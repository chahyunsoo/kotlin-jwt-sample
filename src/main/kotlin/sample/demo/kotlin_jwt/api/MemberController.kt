package sample.demo.kotlin_jwt.api

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sample.demo.kotlin_jwt.api.dto.request.MemberRequest
import sample.demo.kotlin_jwt.domain.MemberService

@RequestMapping("/api/member")
@RestController
class MemberController(
        private val memberService: MemberService
) {
    @PostMapping("/sign-up")
    fun singUp(@RequestBody memberRequest: MemberRequest): ResponseEntity<String> {
        return ResponseEntity.ok(memberService.signUp(memberRequest))

    }

}