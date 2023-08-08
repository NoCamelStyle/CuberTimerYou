package nocamelstyle.cuber.timeryou.ui.screens.timer

import androidx.compose.runtime.Immutable
import nocamelstyle.cuber.timeryou.dataset.defaultCategoryNames
import nocamelstyle.cuber.timeryou.dataset.defaultCubeNames

interface TimerContract {

    sealed class Event {
        object ClickTimer: Event()
        object SelectType: Event()
        object SelectCategory: Event()
        object RegenerateScramble: Event()
        object SetScramble: Event()
    }

    sealed class Effect {

    }

    @Immutable
    data class State(
        val cubeName: String,
        val cubeCategory: String,
        val scrumble: String,
        val time: Int,

        // left column
        val bestTime: Int?,
        val averageTime: Int?,
        val tryCount: Int,
        val dispersion: Int?,


        // right column
        val ao5: Int?,
        val ao12: Int?,
        val ao50: Int?,
        val ao100: Int?,
    ) {

        companion object {
            fun getStub() = State(
                cubeName = defaultCubeNames.first(),
                cubeCategory = defaultCategoryNames.first(),
                scrumble = "L2 B2 L2 D2 L2 U' B2 L2 D' F2 U' L U B U B L' R B' U2 L2",
                time = 0,
                bestTime = 10_000,
                averageTime = 10_000,
                tryCount = 15,
                dispersion = 2_000,
                ao5 = 10_000,
                ao12 = 10_000,
                ao50 = null,
                ao100 = null
            )
        }

    }
}