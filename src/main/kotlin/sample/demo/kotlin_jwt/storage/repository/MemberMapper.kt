package sample.demo.kotlin_jwt.storage.repository

import sample.demo.kotlin_jwt.domain.Member
import sample.demo.kotlin_jwt.storage.entity.Gender
import sample.demo.kotlin_jwt.storage.entity.MemberEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object MemberMapper {
    fun toEntity(member: Member): MemberEntity {
        return MemberEntity(
                id = member.id,
                loginId = member.loginId!!,
                password = member.password!!,
                nickname = member.nickname!!,
                birthDate = LocalDate.parse(member.birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                gender = Gender.valueOf(member.gender!!),
                email = member.email!!
        )
    }

    fun toModel(memberEntity: MemberEntity?): Member? {
        return memberEntity?.let {
            Member(
                    id = memberEntity.id,
                    loginId = memberEntity.loginId,
                    password = memberEntity.password,
                    nickname = memberEntity.nickname,
                    birthDate = memberEntity.birthDate.toString(),
                    gender = memberEntity.gender.name,
                    email = memberEntity.email
            )
        }
    }
}