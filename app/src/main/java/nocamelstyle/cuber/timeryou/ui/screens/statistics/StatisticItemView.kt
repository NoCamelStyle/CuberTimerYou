package nocamelstyle.cuber.timeryou.ui.screens.statistics

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nocamelstyle.cuber.timeryou.dataset.Record
import nocamelstyle.cuber.timeryou.extensions.toFormattedTime
import java.text.SimpleDateFormat
import java.util.Locale

//private val dateFormat = SimpleDateFormat("dd/MMM", Locale.getDefault())

@Composable
fun StatisticItemView(item: Record, remove: () -> Unit) {
//    Box(
//        Modifier
//            .padding(10.dp)
//            .border(BorderStroke(1.dp, Color.Black))
//            .height(60.dp)
//            .fillMaxWidth()) {
//        Text(
//            text = dateFormat.format(Date(item.saveDate)),
//            modifier = Modifier.align(Alignment.TopStart)
//        )
//        Text(text = item.time.toFormattedTime() ?: "", modifier = Modifier.align(Alignment.Center))
//    }
    OutlinedButton(
        onClick = remove,
        modifier = Modifier,
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text = item.time.toFormattedTime() ?: "")
    }
}