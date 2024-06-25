package sample.demo.kotlin_jwt.storage.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(
        uniqueConstraints =[UniqueConstraint(name = "uk_member_login_id",columnNames=["login_id"])]
)
class MemberEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(name = "login_id", length = 30, updatable = false)
        val loginId: String,

        @Column(name = "password", length = 30)
        var password: String,

        @Column(name = "nickname", length = 20)
        var nickname: String,

        @Column(name = "birth_date")
        @Temporal(TemporalType.DATE)
        var birthDate: LocalDate,

        @Column(name = "gender")
        @Enumerated(EnumType.STRING)
        var gender: Gender,

        @Column(name = "email")
        var email: String
)