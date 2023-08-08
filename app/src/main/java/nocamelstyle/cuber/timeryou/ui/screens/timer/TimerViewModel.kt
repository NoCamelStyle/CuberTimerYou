package nocamelstyle.cuber.timeryou.ui.screens.timer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nocamelstyle.cuber.timeryou.MainActivity
import nocamelstyle.cuber.timeryou.dataset.Record
import nocamelstyle.cuber.timeryou.repository.LocalSettingsRepository
import java.util.Date

class TimerViewModel : ViewModel() {

    private val storageRepository = LocalSettingsRepository(MainActivity.contextTmp!!)
    private val databaseRepository get() = MainActivity.recordDao!!

    private val _state = MutableStateFlow(
        TimerContract.State(
            cubeName = storageRepository.cubeName,
            cubeCategory = storageRepository.cubeCategory,
            scrumble = "",
            time = 0,

            bestTime = null,
            averageTime = null,
            tryCount = 0,
            dispersion = null,

            ao5 = null,
            ao12 = null,
            ao50 = null,
            ao100 = null
        )
    )
    val state = _state.asStateFlow()

    init {
        updateScreen()
    }

    private val isStartedTimer get() = timerJob?.isActive == true

    private var timerJob: Job? = null
    fun handleEvent(event: TimerContract.Event) {
        when (event) {
            TimerContract.Event.ClickTimer -> {
                Log.e("vm", "handleEvent: ClickTimer")
                if (isStartedTimer) {
                    stopTimer()
                } else {
                    startTimer()
                }

            }

            TimerContract.Event.SelectCategory -> TODO()
            TimerContract.Event.SelectType -> TODO()
            TimerContract.Event.RegenerateScramble -> TODO()
            TimerContract.Event.SetScramble -> TODO()
        }
    }

    private fun stopTimer() {
        Log.e("vm", "if")
        timerJob?.cancel()
        timerJob = null
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.insert(
                Record(
                    time = state.value.time,
                    penalty = "",
                    cubeType = storageRepository.cubeName,
                    cubeCategory = storageRepository.cubeCategory,
                    saveDate = Date().time
                )
            )
        }
        updateScreen()
    }

    private fun startTimer() {
        Log.e("vm", "else")
        timerJob?.cancel()
        _state.update { it.copy(time = 0) }
        timerJob = viewModelScope.launch {
            while (true) {
                delay(20)
                _state.update { it.copy(time = it.time + 20) }
            }
        }
    }

    private fun updateScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            val history = databaseRepository.getBy(storageRepository.cubeName, storageRepository.cubeName).map { it.time }
            _state.emit(
                TimerContract.State(
                    cubeName = storageRepository.cubeName,
                    cubeCategory = storageRepository.cubeCategory,
                    scrumble = "",
                    time = _state.value.time,

                    bestTime = history.minOrNull(),
                    averageTime = history.average().toInt(),
                    tryCount = history.size,
                    dispersion = null,

                    ao5 = history.takeLast(5).takeIf { it.size >= 5 }?.average()?.toInt(),
                    ao12 = history.takeLast(12).takeIf { it.size >= 12 }?.average()?.toInt(),
                    ao50 = history.takeLast(50).takeIf { it.size >= 50 }?.average()?.toInt(),
                    ao100 = history.takeLast(100).takeIf { it.size >= 100 }?.average()?.toInt()
                )
            )
        }
    }

}