package `in`.mrkaydev.animall.utils

import java.util.*

object CommonUtils {
    fun subtractDaysFromDate(days: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -days)
        return calendar.time
    }

    fun Date.toLong():Long {
        return this.time
    }
}