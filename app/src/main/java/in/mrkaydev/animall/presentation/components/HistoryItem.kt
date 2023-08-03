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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.mrkaydev.animall.database.MilkSaleEntity
import `in`.mrkaydev.animall.ui.theme.appFontRegular
import `in`.mrkaydev.animall.ui.theme.getTextStyle

@Composable
fun HistoryItem(item: MilkSaleEntity) {
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .background(color = Color(0x33D9D9D9), shape = RoundedCornerShape(size = 12.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Order no : ${item.id.toString()}",
            style = getTextStyle(
                fontSize = 18.sp,
                fontFamily = appFontRegular,
                textColor = Color.Black,
            ), modifier = Modifier.padding(top=16.dp)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding()
        ) {
            TextCell(
                textTop = { "Litre" },
                textBottom = { "${item.quantity} litre" },
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
                textTop = { "Rate per litre" },
                textBottom = { "₹ ${item.quantity}" },
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
                    .border(
                        1.dp,
                        Color.Gray.copy(alpha = 0.1f),
                        RectangleShape
                    )
            )
            TextCell(
                textTop = { "Total price" },
                textBottom = { "₹ ${item.quantity}" },
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
                    .border(
                        1.dp,
                        Color.Gray.copy(alpha = 0.1f),
                        RoundedCornerShape( bottomEnd = 12.dp)
                    )
            )
        }
    }
}