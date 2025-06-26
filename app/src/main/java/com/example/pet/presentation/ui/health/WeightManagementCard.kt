package com.example.pet.presentation.ui.health

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf

@Composable
fun WeightManagementCard(data: List<Float>) {
    val entries = data.mapIndexed { index, value -> entryOf(index.toFloat(), value) }
    val chartModelProducer = ChartEntryModelProducer(entries)

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(text = "Weight: ${data.last()} kg")
            Chart(
                chart = lineChart(),
                chartModelProducer = chartModelProducer,
                startAxis = rememberStartAxis(),
                bottomAxis = rememberBottomAxis(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}