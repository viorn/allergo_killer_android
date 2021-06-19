package com.allergokiller.android.events

class ErrorEvent(
    override val message: String,
    val throwable: Throwable? = null
): MessageEvent(message), Event {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ErrorEvent) return false

        if (message != other.message) return false
        if (throwable != other.throwable) return false

        return true
    }

    override fun hashCode(): Int {
        var result = message.hashCode()
        result = 31 * result + (throwable?.hashCode() ?: 0)
        return result
    }
}