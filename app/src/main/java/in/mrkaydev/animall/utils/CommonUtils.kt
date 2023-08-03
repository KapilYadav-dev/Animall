package `in`.mrkaydev.animall.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.jaikeerthick.composable_graphs.color.LinearGraphColors
import com.jaikeerthick.composable_graphs.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.style.LinearGraphVisibility
import `in`.mrkaydev.animall.ui.theme.Teal200
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
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
    @Composable
    fun ScreenWidthInDp(): Float {
        val configuration = LocalConfiguration.current
        return configuration.screenWidthDp.toFloat()
    }

    fun localDateTimeToDate(localDateTime: LocalDateTime): Date {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
    }

    fun dateToLocalDateTime(date: Date): LocalDateTime {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
    }
    fun getDaysIncludingToday(days:Int): List<String> {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("EEEE")
        val daysList = mutableListOf<String>()

        for (i in 0 until days) {
            daysList.add(today.minusDays(i.toLong()).format(formatter))
        }

        return daysList.reversed()
    }
    fun getDataRangeList() = listOf(
        "Total","Daily","Weekly","Monthly","Yearly"
    )
    fun String.removeValuesAfterTwoDecimalPlaces(): String {
        val regex = """^(\d+\.\d{0,2}).*""".toRegex()
        val matchResult = regex.find(this)
        return matchResult?.groupValues?.get(1) ?: this
    }
    val style = LineGraphStyle(
        visibility = LinearGraphVisibility(
            isHeaderVisible = true,
            isYAxisLabelVisible = true,
            isCrossHairVisible = true
        ),
        colors = LinearGraphColors(
            lineColor = Teal200,
            pointColor = Teal200,
            clickHighlightColor = Teal200,
            fillGradient = Brush.verticalGradient(
                listOf(Teal200.copy(alpha = 0.4f), Color.White)
            )
        ),
        height = 160.dp,
    )
}