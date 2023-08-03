package `in`.mrkaydev.animall.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

fun getTextStyle(textColor:Color= Color.Black,fontSize: TextUnit, fontFamily: FontFamily, textAlign: TextAlign=TextAlign.Start): TextStyle {
    return TextStyle(color = textColor, fontFamily=fontFamily, fontSize = fontSize, textAlign = textAlign)
}