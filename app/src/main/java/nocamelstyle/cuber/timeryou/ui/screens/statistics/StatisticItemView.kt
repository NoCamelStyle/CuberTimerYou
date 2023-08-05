package nocamelstyle.cuber.timeryou.ui.screens.statistics

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import nocamelstyle.cuber.timeryou.dataset.Record
import nocamelstyle.cuber.timeryou.extensions.toFormattedTime

@Composable
fun StatisticItemView(item: Record) {
    Button(onClick = {  }) {
        Text(text = item.time.toFormattedTime() ?: "")
    }
}