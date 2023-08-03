package `in`.mrkaydev.animall.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaikeerthick.composable_graphs.composables.LineGraph
import com.jaikeerthick.composable_graphs.data.GraphData
import `in`.mrkaydev.animall.ui.theme.appFontBold
import `in`.mrkaydev.animall.ui.theme.getTextStyle
import `in`.mrkaydev.animall.utils.CommonUtils

@Composable
fun Graph(yAxisName: String, xAxisList: List<Long>, yAxisList: List<Double>) {
    Text(
        text = yAxisName, style = getTextStyle(
            Color.Black, 18.sp,
            appFontBold
        ),
        modifier = Modifier.padding(16.dp)
    )
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(Modifier.fillMaxWidth(0.8f).height(160.dp)) {
                LineGraph(
                    xAxisData = xAxisList.map {
                        GraphData.String(it.toString())
                    },
                    yAxisData = yAxisList,
                    style = CommonUtils.style
                )
            }
            Text(text = yAxisName, modifier = Modifier.graphicsLayer(rotationZ = 270f), fontFamily = appFontBold, fontSize = 12.sp)
        }
        Text(text = "Order No", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontFamily = appFontBold, fontSize = 12.sp)
    }
}