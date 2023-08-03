package `in`.mrkaydev.animall.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.mrkaydev.animall.ui.theme.appFontBold
import `in`.mrkaydev.animall.ui.theme.getTextStyle


@Composable
fun Picker(modifier: Modifier, onClick: () -> Unit, dateRange: () -> String) {
    Row(modifier = modifier.clickable {
        onClick()
    }, verticalAlignment = Alignment.CenterVertically) {
        Text(
            dateRange(),
            style = getTextStyle(Color.Black.copy(alpha = 0.6f), 14.sp, appFontBold),
            modifier = Modifier.padding(start = 8.dp)
        )
        Icon(imageVector = Icons.Filled.KeyboardArrowDown, "")
    }
}
