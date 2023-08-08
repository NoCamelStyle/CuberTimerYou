package nocamelstyle.cuber.timeryou.ui.screens.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nocamelstyle.cuber.timeryou.MainActivity
import nocamelstyle.cuber.timeryou.repository.LocalSettingsRepository

class StatisticsVM : ViewModel() {

    private val database get() = MainActivity.recordDao!!
    private val storageRepository = LocalSettingsRepository(MainActivity.contextTmp!!)


    private val _state = MutableStateFlow(
        StatisticsState(
            listOf(),
            "",
            cubeName = storageRepository.cubeName,
            cubeCategory = storageRepository.cubeCategory
        )
    )
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.emit(
                StatisticsState(
                    database.getBy(storageRepository.cubeName, storageRepository.cubeCategory),
                    "",
                    cubeName = storageRepository.cubeName,
                    cubeCategory = storageRepository.cubeCategory
                )
            )
        }
    }

}