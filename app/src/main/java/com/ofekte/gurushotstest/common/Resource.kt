package com.ofekte.gurushotstest.common

sealed class Resource<T>(open val data: T?, open val message: String?) {

    class Loading<T>(override val data: T? = null) : Resource<T>(data, null)

    class Success<T>(override val data: T, message: String? = null) : Resource<T>(data, message)

    class Error<T>(override val message: String, override val data: T? = null) : Resource<T>(data, message)
}