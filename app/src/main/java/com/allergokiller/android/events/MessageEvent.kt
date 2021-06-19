package com.allergokiller.android.events

open class MessageEvent(
    open val message: String
): Event {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MessageEvent) return false

        if (message != other.message) return false

        return true
    }

    override fun hashCode(): Int {
        return message.hashCode()
    }
}