package com.rikezero.dektek.domain.base

sealed class DekTekFailure(
    message: String?,
    cause: Throwable?
): Throwable(message ?: cause?.message, cause) {

    private companion object {
        const val GENERIC_ERROR = "An error has occurred"
    }

    override val message: String?
        get() = super.message ?: GENERIC_ERROR

    class UnknownFailure(cause: Throwable, message: String? = null) : DekTekFailure(message, cause)

    class NetworkingFailure(
        val error: Error,
        val httpCode: Int? = null,
        message: String? = null,
        cause: Throwable? = null
    ): DekTekFailure(message, cause) {

        class Error(
            val code: String,
            val message: String
        )
    }
}

