package nocamelstyle.cuber.timeryou.ui.screens.statistics

import androidx.compose.runtime.Immutable
import nocamelstyle.cuber.timeryou.dataset.Record

@Immutable
data class StatisticsState(
    val records: List<Record>,
    val searchToken: String,
    val cubeName: String,
    val cubeCategory: String
)