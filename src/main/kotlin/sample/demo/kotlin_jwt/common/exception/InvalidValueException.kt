package sample.demo.kotlin_jwt.common.exception

class InvalidValueException(
        val filedName: String = "",
        message: String = "Invalid Input Value",
) :RuntimeException(message){
}