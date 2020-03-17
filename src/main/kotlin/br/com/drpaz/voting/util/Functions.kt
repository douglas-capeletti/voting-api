package br.com.drpaz.voting.util

import br.com.drpaz.voting.util.exception.MoreThanOneException
import br.com.drpaz.voting.util.exception.ResourceNotFoundException
import java.time.LocalDateTime

object Functions {

    fun String?.onlyDigits() = if (this.isNullOrEmpty()) "" else this.replace("\\D".toRegex(), "")

    fun LocalDateTime?.isPaste() = this?.isAfter(LocalDateTime.now()) ?: false

    inline fun <reified T> List<T>.onlyOneOrThrow(): T {
        return when (this.size) {
            0 -> throw ResourceNotFoundException(T::class.simpleName ?: "")
            1 -> this.first()
            else -> throw MoreThanOneException(T::class.simpleName ?: "")
        }
    }

}