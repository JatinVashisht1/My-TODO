package com.example.mytodo.core

sealed class Resources<T>(val data: T?, val message: String? ){
    class Loading<T> : Resources<T>(data = null, message = null)
    class Success<T>(data: T?) : Resources<T>(data = data, null)
    class Error<T>(message: String) : Resources<T>(data = null, message = message)
}
