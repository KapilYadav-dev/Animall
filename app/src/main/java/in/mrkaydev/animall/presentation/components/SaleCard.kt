package `in`.mrkaydev.animall.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.mrkaydev.animall.ui.theme.appFontBold
import `in`.mrkaydev.animall.ui.theme.appFontRegular
import `in`.mrkaydev.animall.ui.theme.getTextStyle
import `in`.mrkaydev.animall.utils.CommonUtils.removeValuesAfterTwoDecimalPlaces


@Composable
fun SaleCard(
    totalQuantityForPeriod: () -> String,
    totalRevenueForPeriod: () -> String,
    totalUserForPeriod: () -> String,
    totalAvgPriceForPeriod: () -> String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color(0x33D9D9D9), shape = RoundedCornerShape(size = 12.dp))
    ) {
        Text(
            text = "Total milk sale",
            style = getTextStyle(
                fontSize = 18.sp,
                fontFamily = appFontRegular,
                textColor = Color(0x80000000),
            ),
            modifier = Modifier.padding(start = 24.dp, top = 16.dp)
        )
        Text(
            text = "₹ ${totalRevenueForPeriod().removeValuesAfterTwoDecimalPlaces()} / ltr ${totalQuantityForPeriod().removeValuesAfterTwoDecimalPlaces()}",
            style = getTextStyle(
                fontSize = 18.sp,
                fontFamily = appFontBold,
                textColor = Color(0xFF3CBE27),
            ),
            modifier = Modifier.padding(start = 24.dp, top = 16.dp)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding()
        ) {
            TextCell(
                textTop = { "Total user covered" },
                textBottom = { totalUserForPeriod() },
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
                    .border(
                        1.dp,
                        Color.Gray.copy(alpha = 0.1f),
                        RoundedCornerShape(bottomStart = 12.dp)
                    )
            )
            TextCell(
                textTop = { "Average price/litre" },
                textBottom = { "₹ ${totalAvgPriceForPeriod()}" },
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
                    .border(
                        1.dp,
                        Color.Gray.copy(alpha = 0.1f),
                        RoundedCornerShape(bottomEnd = 12.dp)
                    )
            )
        }
    }
}

@Composable
fun TextCell(textTop: () -> String, textBottom: () -> String, modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Text(
            text = textTop(), style = getTextStyle(
                Color.Black.copy(0.4f),
                fontSize = 16.sp,
                fontFamily = appFontRegular,
                textAlign = TextAlign.Center
            ), modifier = Modifier.padding(top = 32.dp)
        )
        Text(
            text = textBottom(), style = getTextStyle(
                Color.Black,
                fontSize = 18.sp,
                fontFamily = appFontRegular,
                textAlign = TextAlign.Center
            ), modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
