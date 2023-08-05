package nocamelstyle.cuber.timeryou.ui.screens.statistics

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StatisticsVM : ViewModel() {

    private val _state = MutableStateFlow(StatisticsState(listOf(), ""))
    val state = _state.asStateFlow()

}