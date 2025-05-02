package com.emrekizil.core.common

sealed interface DataSource <out T> {
    data class Success<T>(val data: T) : DataSource<T>
    data class Error(val exception: Exception) : DataSource<Nothing>
    data object Loading : DataSource<Nothing>
}