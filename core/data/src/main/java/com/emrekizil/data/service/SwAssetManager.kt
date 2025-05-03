package com.emrekizil.data.service

import java.io.InputStream

fun interface SwAssetManager {
    fun open(fileName: String): InputStream
}