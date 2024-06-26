package sample.demo.kotlin_jwt.storage.repository

import org.springframework.stereotype.Repository
import sample.demo.kotlin_jwt.domain.Member
import sample.demo.kotlin_jwt.domain.MemberRepository

@Repository
class MemberRepositoryImpl(
        private val memberJpaRepository: MemberJpaRepository
) : MemberRepository {
    override fun findByLoginId(loginId: String?): Member? {
        return MemberMapper.toModel(
                memberJpaRepository.findByLoginId(loginId)
        )
    }

    override fun singUp(member: Member): Member? {
        return MemberMapper.toModel(
                memberJpaRepository.save(MemberMapper.toEntity(member))
        )
    }
}