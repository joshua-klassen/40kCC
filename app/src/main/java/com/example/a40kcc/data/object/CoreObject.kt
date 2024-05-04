package com.example.a40kcc.data.`object`

interface CoreObject {
    fun getCoreColumns(): Map<String, String>
    fun getDetailColumns(): Map<String, String>
    fun getDisplayName(): String {
        return ""
    }
}