package `in`.mrkaydev.animall.presentation.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import `in`.mrkaydev.animall.R
import `in`.mrkaydev.animall.presentation.components.Graph
import `in`.mrkaydev.animall.presentation.components.HistoryItem
import `in`.mrkaydev.animall.presentation.components.Picker
import `in`.mrkaydev.animall.presentation.components.SaleCard
import `in`.mrkaydev.animall.presentation.viewmodels.MilkSaleViewModel
import `in`.mrkaydev.animall.ui.theme.appFontBold
import `in`.mrkaydev.animall.ui.theme.getTextStyle
import `in`.mrkaydev.animall.utils.CommonUtils.getDataRangeList
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(navigator: NavHostController, viewModel: MilkSaleViewModel) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true,
    )
    BackHandler {

    }
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            MilkSaleScreen(viewModel) {
                coroutineScope.launch { modalSheetState.hide() }
            }
        }
    ) {
        val dataRangeList = getDataRangeList()
        var currentItemIndex by remember { mutableStateOf(0) }
        val context = LocalContext.current
        val historyListSize = 5

        val totalQuantityTillNow by viewModel.totalQuantityTillNow.collectAsState(initial = 0.0)
        val totalQuantityForPeriod by viewModel.totalQuantityPeriod.collectAsState(initial = 0.0)

        val totalRevenueTillNow by viewModel.totalRevenueTillNow.collectAsState(initial = 0.0)
        val totalRevenueForPeriod by viewModel.totalRevenuePeriod.collectAsState(initial = 0.0)


        val averagePriceTillNow by viewModel.averagePricePeriodTillNow.collectAsState(initial = 0.0)
        val averagePriceForPeriod by viewModel.averagePricePeriod.collectAsState(initial = 0.0)


        val totalRevenueList by viewModel.milkSalesTotalTillNow.collectAsState(initial = emptyList())
        val totalRevenueListForPeriod by viewModel.milkSalesForPeriod.collectAsState(initial = emptyList())

        var totalQuantity by remember {
            mutableStateOf("")
        }
        var totalRevenue by remember {
            mutableStateOf("")
        }
        var totalUser by remember {
            mutableStateOf("")
        }
        var totalAvgPrice by remember {
            mutableStateOf("")
        }

        when (dataRangeList[currentItemIndex]) {
            "Total" -> {
                totalQuantity = totalQuantityTillNow.toString()
                totalRevenue = totalRevenueTillNow.toString()
                totalUser = totalRevenueList.size.toString()
                totalAvgPrice = averagePriceTillNow.toString()
            }
            "Daily" -> {
                viewModel.getAllDataForDate(1)
                totalQuantity = totalQuantityForPeriod.toString()
                totalRevenue = totalRevenueForPeriod.toString()
                totalUser = totalRevenueListForPeriod.size.toString()
                totalAvgPrice = averagePriceForPeriod.toString()
            }
            "Weekly" -> {
                viewModel.getAllDataForDate(7)
                totalQuantity = totalQuantityForPeriod.toString()
                totalRevenue = totalRevenueForPeriod.toString()
                totalUser = totalRevenueListForPeriod.size.toString()
                totalAvgPrice = averagePriceForPeriod.toString()
            }
            "Monthly" -> {
                viewModel.getAllDataForDate(30)
                totalQuantity = totalQuantityForPeriod.toString()
                totalRevenue = totalRevenueForPeriod.toString()
                totalUser = totalRevenueListForPeriod.size.toString()
                totalAvgPrice = averagePriceForPeriod.toString()
            }
            "Yearly" -> {
                viewModel.getAllDataForDate(365)
                totalQuantity = totalQuantityForPeriod.toString()
                totalRevenue = totalRevenueForPeriod.toString()
                totalUser = totalRevenueListForPeriod.size.toString()
                totalAvgPrice = averagePriceForPeriod.toString()
            }
        }
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Statistics till now", style = getTextStyle(
                        Color.Black, 18.sp,
                        appFontBold
                    )
                )
                Picker(
                    modifier = Modifier
                        .background(
                            color = Color(0x66D9D9D9),
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                        .padding(4.dp),
                    onClick = {
                        currentItemIndex = (currentItemIndex + 1) % dataRangeList.size
                    }
                ) { dataRangeList[currentItemIndex] }
            }
            SaleCard(
                totalQuantityForPeriod = { totalQuantity },
                totalRevenueForPeriod = { totalRevenue },
                totalUserForPeriod = { totalUser },
                totalAvgPriceForPeriod = { totalAvgPrice }
            )
            val xAxisList =
                totalRevenueList.takeLast(if (totalRevenueList.size < historyListSize) totalRevenueList.size else historyListSize)
                    .mapNotNull { it.id }
            val yAxisListFirst = totalRevenueList
                .takeLast(if (totalRevenueList.size < historyListSize) totalRevenueList.size else historyListSize)
                .reversed().map { it.quantity }
            val yAxisListSecond = totalRevenueList
                .takeLast(if (totalRevenueList.size < historyListSize) totalRevenueList.size else historyListSize)
                .reversed().map { it.totalAmount }
            if (xAxisList.isNotEmpty() && yAxisListFirst.isNotEmpty() && yAxisListSecond.isNotEmpty() && xAxisList.size > 2) {
                Graph("Quantity of Milk (ltr)", xAxisList, yAxisListFirst)
                Graph("Total Milk sold (₹)", xAxisList, yAxisListSecond)
            }
            Text(
                text = "Recent History", style = getTextStyle(
                    Color.Black, 18.sp,
                    appFontBold
                ),
                modifier = Modifier.padding(16.dp)
            )
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    totalRevenueList
                        .takeLast(if (totalRevenueList.size < historyListSize) totalRevenueList.size else historyListSize)
                        .reversed()
                ) { item ->
                    HistoryItem(item)
                }
            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            Icon(
                modifier = Modifier
                    .padding(32.dp)
                    .size(64.dp)
                    .align(Alignment.BottomEnd)
                    .clickable {
                        coroutineScope.launch {
                            if (modalSheetState.isVisible)
                                modalSheetState.hide()
                            else
                                modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                        }
                    },
                painter = painterResource(
                    id = R.drawable.ic_fab
                ), contentDescription = "",
                tint = Color.Black
            )
        }
    }
}