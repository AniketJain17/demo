package com.myapplication.common.model

/**
 * You data model that will be used throughout the app.
 */
data class Reminder(
    val name: String,
    val password: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Reminder

        if (name != other.name) return false
        if (password != other.password) return false

        return true
    }
}
