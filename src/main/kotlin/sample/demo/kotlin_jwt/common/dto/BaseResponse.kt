package sample.demo.kotlin_jwt.common.dto

import sample.demo.kotlin_jwt.common.status.ResultCode

data class BaseResponse<T>(
        val resultCode: String = ResultCode.SUCCESS.name,
        val data: T? = null,
        val message: String = ResultCode.SUCCESS.message
)
