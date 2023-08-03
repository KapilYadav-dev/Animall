package `in`.mrkaydev.animall.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.mrkaydev.animall.ui.theme.appFontRegular
import `in`.mrkaydev.animall.ui.theme.getTextStyle


@Composable
fun InputCell(
    text: () -> String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    inputValue: () -> Double,
    isEnabled: Boolean = true
) {
    var textState = inputValue().toString()
    val pattern = remember { Regex("\\d*(\\.\\d{0,2})?") }
    val maxChar = 7
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = text(), style = getTextStyle(
                Color.Black.copy(0.4f),
                fontSize = 16.sp,
                fontFamily = appFontRegular,
                textAlign = TextAlign.Center
            ), modifier = Modifier.padding(top = 32.dp)
        )
        TextField(
            value = textState,
            onValueChange = {
                if (it.isEmpty() || it.matches(pattern) && it.length <= maxChar) {
                    textState = it
                    onValueChange(it)
                }
            },
            textStyle = getTextStyle(
                Color.Black, 18.sp, appFontRegular, textAlign = TextAlign.Center
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number,
            ),
            modifier = Modifier.padding(4.dp),
            enabled = isEnabled,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            )
        )
    }
}