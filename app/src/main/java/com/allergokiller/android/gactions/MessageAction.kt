package com.allergokiller.android.gactions

open class MessageAction(
    open val message: String
): Action {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MessageAction) return false

        if (message != other.message) return false

        return true
    }

    override fun hashCode(): Int {
        return message.hashCode()
    }
}