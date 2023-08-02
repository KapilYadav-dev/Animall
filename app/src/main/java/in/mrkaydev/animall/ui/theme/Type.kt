package `in`.mrkaydev.animall.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import `in`.mrkaydev.animall.R

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    )
)

val appFontRegular = FontFamily(Font(R.font.regular))
val appFontBold = FontFamily(Font(R.font.bold))

fun getTextStyle(fontSize: TextUnit,fontFamily: FontFamily): TextStyle {
    return TextStyle(fontFamily=fontFamily, fontSize = fontSize)
}