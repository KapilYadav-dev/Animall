package `in`.mrkaydev.animall.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.commandiron.wheel_picker_compose.WheelDateTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import `in`.mrkaydev.animall.database.MilkSaleEntity
import `in`.mrkaydev.animall.presentation.components.InputCell
import `in`.mrkaydev.animall.presentation.viewmodels.MilkSaleViewModel
import `in`.mrkaydev.animall.ui.theme.Teal200
import `in`.mrkaydev.animall.ui.theme.appFontBold
import `in`.mrkaydev.animall.ui.theme.getTextStyle
import `in`.mrkaydev.animall.utils.CommonUtils
import `in`.mrkaydev.animall.utils.CommonUtils.dateToLocalDateTime
import `in`.mrkaydev.animall.utils.CommonUtils.localDateTimeToDate
import `in`.mrkaydev.animall.utils.CommonUtils.toDoubleSafe
import `in`.mrkaydev.animall.utils.CommonUtils.toMilliSeconds
import java.time.LocalDate
import java.time.LocalDateTime
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
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, 2022)
    calendar.set(Calendar.MONTH, 12)
    val calendarMax = Calendar.getInstance()
    calendarMax.set(Calendar.YEAR, 2032)
    calendarMax.set(Calendar.MONTH, 12)
    var selectedDate by remember { mutableStateOf(Date()) }

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
                text = { "Litre" },
                onValueChange = {
                    quantityInput = it.toDoubleSafe()
                },
                inputValue = { quantityInput },
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray.copy(alpha = 0.1f), RectangleShape)
            )
            InputCell(
                text = { "Rate per litre" },
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
        Spacer(modifier =Modifier.height(8.dp))
        Text(
            text = "Pick date and time",
            style = getTextStyle(Color.Black, 22.sp, appFontBold),
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier =Modifier.height(8.dp))
        WheelDateTimePicker(
            timeFormat = TimeFormat.AM_PM,
            minDateTime = LocalDateTime.of(
                2005, 10, 20, 5, 30
            ),
            maxDateTime = LocalDateTime.of(
                2023, LocalDate.now().monthValue,30,12,59
            ),
            textStyle = TextStyle(fontFamily = appFontBold),
            startDateTime = dateToLocalDateTime(selectedDate),
            size = DpSize(CommonUtils.screenWidthInDp().dp, 100.dp),
            selectorProperties = WheelPickerDefaults.selectorProperties(
                enabled = true,
                shape = RoundedCornerShape(0.dp),
                color = Teal200.copy(alpha = 0.2f),
                border = BorderStroke(2.dp, Teal200)
            )
        ) {
            selectedDate=localDateTimeToDate(it)
        }
        Spacer(modifier =Modifier.height(8.dp))
        Button(
            onClick = {
                if (quantityInput == 0.0) {
                    Toast.makeText(context, "Milk quantity can't be 0", Toast.LENGTH_LONG).show()
                    return@Button
                }
                if(quantityInput>1000.0) {
                    Toast.makeText(context, "Milk quantity can't exceed 1000 litres", Toast.LENGTH_LONG).show()
                    return@Button
                }
                if (perUnitInput == 0.0) {
                    Toast.makeText(context, "Price per unit can't be 0", Toast.LENGTH_LONG).show()
                    return@Button
                }
                if(perUnitInput>100.0) {
                    Toast.makeText(context, "Price can't exceed 100 litres", Toast.LENGTH_LONG).show()
                    return@Button
                }
                val data = MilkSaleEntity(
                    null,
                    quantityInput,
                    perUnitInput,
                    totalRevenue,
                    selectedDate.toMilliSeconds(),
                    selectedDate.toString()
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
                text = "Record",
                style = getTextStyle(Color.White, 22.sp, appFontBold, TextAlign.Center),
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}