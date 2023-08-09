package nocamelstyle.cuber.timeryou.ui.screens.statistics

import android.util.Log
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
        updateScreen()
    }

    private fun updateScreen() {
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

    fun handleEvent(event: StatisticsContract.Event) {
        when (event) {
            is StatisticsContract.Event.SelectType -> {
                storageRepository.cubeName = event.type
                updateScreen()
            }
            is StatisticsContract.Event.SelectCategory -> {
                storageRepository.cubeCategory = event.type
                updateScreen()
            }

            StatisticsContract.Event.Resume -> updateScreen()

            is StatisticsContract.Event.Remove -> {
                viewModelScope.launch(Dispatchers.IO) {
                    runCatching { database.delete(event.item) }
                        .onFailure { Log.e("delete", "hmm", it) }
                    updateScreen()
                }
            }
        }
    }

}