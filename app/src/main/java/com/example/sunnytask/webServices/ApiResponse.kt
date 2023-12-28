package com.example.sunnytask.webServices

sealed class ApiResponse<out T> {
    data class Loading(val isLoading: Boolean) : ApiResponse<Nothing>()
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
}
