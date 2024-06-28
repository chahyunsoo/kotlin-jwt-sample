package sample.demo.kotlin_jwt.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import sample.demo.kotlin_jwt.common.dto.BaseResponse
import sample.demo.kotlin_jwt.common.status.ResultCode

@RestControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun methodArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mutableMapOf<String, String>()
        exception.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage
            errors[fieldName] = errorMessage ?: "Not Excetpion Message"
        }
        return ResponseEntity(BaseResponse(ResultCode.FAIL.name, errors, ResultCode.FAIL.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InvalidValueException::class)
    protected fun inValidException(exception: InvalidValueException): ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mapOf(exception.filedName to (exception.message ?: "Not Excetpion Message"))
        return ResponseEntity(BaseResponse(ResultCode.FAIL.name, errors, ResultCode.FAIL.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    protected fun defaultException(exception: Exception): ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mapOf("미처리 에러" to (exception.message ?: "Not Excetpion Message"))
        return ResponseEntity(BaseResponse(ResultCode.FAIL.name, errors, ResultCode.FAIL.message), HttpStatus.BAD_REQUEST)
    }
}