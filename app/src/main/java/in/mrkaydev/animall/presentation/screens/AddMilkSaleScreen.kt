package `in`.mrkaydev.animall.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.mrkaydev.animall.database.MilkSaleEntity
import `in`.mrkaydev.animall.presentation.viewmodels.MilkSaleViewModel
import `in`.mrkaydev.animall.ui.theme.Teal200
import `in`.mrkaydev.animall.ui.theme.appFontBold
import `in`.mrkaydev.animall.ui.theme.appFontRegular
import `in`.mrkaydev.animall.ui.theme.getTextStyle
import `in`.mrkaydev.animall.utils.CommonUtils.toDoubleSafe
import `in`.mrkaydev.animall.utils.CommonUtils.toMilliSeconds
import java.util.*


@Composable
fun MilkSaleScreen(viewModel: MilkSaleViewModel, onSuccess: () -> Unit) {

    val context = LocalContext.current
    var quantityInput by rememberSaveable { mutableStateOf(0.0) }
    var perUnitInput by rememberSaveable { mutableStateOf(0.0) }
    val totalRevenue by remember(quantityInput, perUnitInput) {
        derivedStateOf {
            quantityInput * perUnitInput
        }
    }
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
            .background(Color.White)
    ) {
        Text(
            text = "Record a sale",
            style = getTextStyle(Color.Black, 22.sp, appFontBold),
            modifier = Modifier.padding(16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            InputCell(
                text = { "Liter" },
                onValueChange = {
                    quantityInput = it.toDoubleSafe()
                },
                inputValue = { quantityInput },
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray.copy(alpha = 0.1f), RectangleShape)
            )
            InputCell(
                text = { "Rate per liter" },
                onValueChange = {
                    perUnitInput = it.toDoubleSafe()
                },
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray.copy(alpha = 0.1f), RectangleShape),
                inputValue = { perUnitInput }
            )
            InputCell(
                text = { "Total price" },
                onValueChange = {},
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray.copy(alpha = 0.1f), RectangleShape),
                inputValue = {
                    totalRevenue
                }
            )
        }
        Button(
            onClick = {
                if (quantityInput == 0.0) {
                    Toast.makeText(context,"Milk quantity can't be 0",Toast.LENGTH_LONG).show()
                    return@Button
                }
                if (perUnitInput == 0.0) {
                    Toast.makeText(context,"Price per unit can't be 0",Toast.LENGTH_LONG).show()
                    return@Button
                }
                val data = MilkSaleEntity(
                    null,
                    quantityInput,
                    perUnitInput,
                    totalRevenue,
                    Date().toMilliSeconds()
                )
                viewModel.insertMilkSale(data)
                Toast.makeText(context, "Sale add successfully", Toast.LENGTH_LONG).show()
                quantityInput = 0.0
                perUnitInput = 0.0
                onSuccess()
            }, colors = ButtonDefaults.buttonColors(
                backgroundColor = Teal200
            ), modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                text = "Add to sales",
                style = getTextStyle(Color.White, 22.sp, appFontBold, TextAlign.Center),
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun InputCell(
    text: () -> String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    inputValue: () -> Double,
    isEnabled: Boolean = true
) {
    var textState = inputValue().toString()
    val pattern = remember { Regex("^(\\d*\\.?\\d*)\$") }
    val maxChar = 8
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally,
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