package `in`.mrkaydev.animall.presentation.screens

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
        val totalRevenueTillNow by viewModel.totalRevenueTillNow.collectAsState(initial = 0.0)
        val totalRevenueList by viewModel.milkSalesTotalTillNow.collectAsState(initial = emptyList())

        var totalQuantityForPeriod by remember {
            mutableStateOf("-")
        }
        var totalRevenueForPeriod by remember {
            mutableStateOf("-")
        }
        var totalUserForPeriod by remember {
            mutableStateOf("-")
        }
        var totalAvgPriceForPeriod by remember {
            mutableStateOf("-")
        }

        when (dataRangeList[currentItemIndex]) {
            "Total" -> {
                totalQuantityForPeriod = totalQuantityTillNow.toString()
                totalRevenueForPeriod = totalRevenueTillNow.toString()
                totalUserForPeriod = totalRevenueList.size.toString()
            }
            "Daily" -> {
                totalQuantityForPeriod = ""
                totalRevenueForPeriod = ""
                totalUserForPeriod = ""
            }
            "Weekly" -> {

            }
            "Monthly" -> {

            }
            "Yearly" -> {

            }
        }
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)) {
            /*
             *coroutineScope.launch {
                    if (modalSheetState.isVisible)
                        modalSheetState.hide()
                    else
                        modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                }
                *    val style = LineGraphStyle(
                visibility = LinearGraphVisibility(
                    isHeaderVisible = true,
                    isYAxisLabelVisible = true,
                    isCrossHairVisible = true
                ),
                colors = LinearGraphColors(
                    lineColor = Teal200,
                    pointColor = Teal200,
                    clickHighlightColor = Color.Black,
                    fillGradient = Brush.verticalGradient(
                        listOf(Teal200.copy(alpha = 0.4f), Color.White)
                    )
                )
            )
            * LineGraph(
                xAxisData = listOf("Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat").map {
                    GraphData.String(it)
                },
                yAxisData = listOf(2000, 40, 60, 450, 700, 30, 50),
                style = style
            )
             */
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
                totalQuantityForPeriod = { totalQuantityForPeriod },
                totalRevenueForPeriod = { totalRevenueForPeriod },
                totalUserForPeriod = { totalUserForPeriod },
                totalAvgPriceForPeriod = { totalAvgPriceForPeriod }
            )
            Text(
                text = "Recent History", style = getTextStyle(
                    Color.Black, 18.sp,
                    appFontBold
                ),
                modifier = Modifier.padding(16.dp)
            )
            LazyColumn(
                Modifier.fillMaxWidth().height(400.dp),
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