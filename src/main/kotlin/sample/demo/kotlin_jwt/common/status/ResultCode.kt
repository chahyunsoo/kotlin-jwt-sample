package sample.demo.kotlin_jwt.common.status

enum class ResultCode(val message: String) {
    SUCCESS("성공 반환"),
    FAIL("실패 반환")
}