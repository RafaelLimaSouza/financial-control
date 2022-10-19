package com.financialcontrol.crosscutting.exceptions

data class ErrorMessageModel(
    val code: String,
    val message: String,
    var details: Map<String, Any>? = null
){
    companion object {
        fun create(message:String, code: String) = ErrorMessageModel(
            code = code,
            message = message,
            details = null
        )

        fun create(message:String, code: String, details: Map<String, Any>) = ErrorMessageModel(
            code = code,
            message = message,
            details = details
        )
    }
}
