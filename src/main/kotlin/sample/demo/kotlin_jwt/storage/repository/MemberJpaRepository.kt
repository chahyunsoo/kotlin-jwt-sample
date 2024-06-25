package sample.demo.kotlin_jwt.storage.repository

import org.springframework.data.jpa.repository.JpaRepository
import sample.demo.kotlin_jwt.storage.entity.MemberEntity

interface MemberJpaRepository : JpaRepository<MemberEntity, Long> {
    fun findByLoginId(loginId: String): MemberEntity?

}