package sample.demo.kotlin_jwt.storage

import jakarta.persistence.*
import sample.demo.kotlin_jwt.domain.Gender
import java.time.LocalDate

@Entity
@Table(
        uniqueConstraints =[UniqueConstraint(name = "uk_member_login_id",columnNames=["login_id"])]
)
class MemberEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,

        @Column(name = "login_id", length = 30, updatable = false)
        val loginId: String,

        @Column(name = "password", length = 30)
        var password: String,

        @Column(name = "name", length = 20)
        var name: String,

        @Column(name = "birth_date")
        @Temporal(TemporalType.DATE)
        var birthDate: LocalDate,

        @Column(name = "gender")
        @Enumerated(EnumType.STRING)
        var gender: Gender,

        @Column(name = "email")
        var email: String
)