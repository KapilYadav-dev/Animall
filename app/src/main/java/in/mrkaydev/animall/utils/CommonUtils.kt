package `in`.mrkaydev.animall.utils

import java.util.*

object CommonUtils {
    fun subtractDaysFromDate(days: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -days)
        return calendar.time
    }

    fun Date.toMilliSeconds() = this.time

    fun String.toDoubleSafe(defaultValue: Double = 0.0): Double {
        return try {
            this.toDouble()
        } catch (e: NumberFormatException) {
            println("Exception ho rha hai jiiiii")
            defaultValue
        }
    }
}