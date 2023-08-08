package nocamelstyle.cuber.timeryou.ui.screens.statistics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import nocamelstyle.cuber.timeryou.dataset.Record
import nocamelstyle.cuber.timeryou.extensions.toFormattedTime
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val dateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())

@Composable
fun StatisticItemView(item: Record) {
    Box(
        Modifier
            .padding(10.dp)
            .border(BorderStroke(1.dp, Color.Black))
            .height(60.dp)
            .fillMaxWidth()) {
        Text(
            text = dateFormat.format(Date(item.saveDate)),
            modifier = Modifier.align(Alignment.TopStart)
        )
        Text(text = item.time.toFormattedTime() ?: "", modifier = Modifier.align(Alignment.Center))
    }
}