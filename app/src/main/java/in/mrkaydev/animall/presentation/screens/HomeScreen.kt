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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import `in`.mrkaydev.animall.presentation.viewmodels.MilkSaleViewModel
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
        viewModel.getTotalQuantity()
        val totalQ by viewModel.totalQuantityTillNow.collectAsState(initial = 0.0)
        val totalR by viewModel.totalRevenueTillNow.collectAsState(initial = 0.0)
        val totalL by viewModel.milkSalesTotalTillNow.collectAsState(initial = 0.0)
        Column(Modifier.fillMaxSize()) {
            Button(onClick = {
                coroutineScope.launch {
                    if (modalSheetState.isVisible)
                        modalSheetState.hide()
                    else
                        modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                }
            }) {

            }
            Text(text = "Total Quantity is $totalQ and total Revenue is $totalR and totalList size is $totalL")
        }
    }
}
