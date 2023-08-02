package nocamelstyle.cuber.timeryou.ui.screens.timer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nocamelstyle.cuber.timeryou.MainActivity
import nocamelstyle.cuber.timeryou.repository.LocalSettingsRepository

class TimerViewModel : ViewModel() {

    private val storageRepository = LocalSettingsRepository(MainActivity.contextTmp!!)

    private val _state = MutableStateFlow(storageRepository.let { store ->
        val history = store.history
        TimerContract.State(
            cubeName = store.cubeName,
            cubeCategory = store.cubeCategory,
            scrumble = "",
            time = 0,

            bestTime = history.minOrNull(),
            averageTime = history.average().toInt(),
            tryCount = history.size,
            dispersion = null,

            ao5 = history.takeLast(5).average().toInt(),
            ao12 = history.takeLast(12).average().toInt(),
            ao50 = history.takeLast(50).average().toInt(),
            ao100 = history.takeLast(100).average().toInt()
        )
    })
    val state = _state.asStateFlow()

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
        }
    }

    private fun stopTimer() {
        Log.e("vm", "if")
        timerJob?.cancel()
        timerJob = null
        storageRepository.addTimer(state.value.time)
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
        viewModelScope.launch {
            _state.emit(
                storageRepository.let { store ->
                    val history = store.history
                    TimerContract.State(
                        cubeName = store.cubeName,
                        cubeCategory = store.cubeCategory,
                        scrumble = "",
                        time = _state.value.time,

                        bestTime = history.minOrNull(),
                        averageTime = history.average().toInt(),
                        tryCount = history.size,
                        dispersion = null,

                        ao5 = history.takeLast(5).average().toInt(),
                        ao12 = history.takeLast(12).average().toInt(),
                        ao50 = history.takeLast(50).average().toInt(),
                        ao100 = history.takeLast(100).average().toInt()
                    )
                }
            )
        }
    }

    init {
        _state
            .onEach {
                Log.e("track", "time: ${it.time}")
            }
            .launchIn(viewModelScope)
    }

}