package `in`.mrkaydev.animall.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jaikeerthick.composable_graphs.color.LinearGraphColors
import com.jaikeerthick.composable_graphs.color.PointHighlight2
import com.jaikeerthick.composable_graphs.composables.LineGraph
import com.jaikeerthick.composable_graphs.data.GraphData
import com.jaikeerthick.composable_graphs.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.style.LinearGraphVisibility
import `in`.mrkaydev.animall.presentation.viewmodels.MilkSaleViewModel
import `in`.mrkaydev.animall.ui.theme.Teal200
import `in`.mrkaydev.animall.utils.CommonUtils
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(navigator: NavHostController, viewModel: MilkSaleViewModel) {
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
        val context = LocalContext.current
        val totalQ by viewModel.totalQuantityTillNow.collectAsState(initial = 0.0)
        val totalR by viewModel.totalRevenueTillNow.collectAsState(initial = 0.0)
        val totalL by viewModel.milkSalesTotalTillNow.collectAsState(initial = 0.0)
        Column(Modifier.fillMaxSize()) {
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

        }
    }
}
