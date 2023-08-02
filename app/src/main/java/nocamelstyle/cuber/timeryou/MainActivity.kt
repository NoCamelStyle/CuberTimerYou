package nocamelstyle.cuber.timeryou

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import nocamelstyle.cuber.timeryou.ui.screens.timer.TimerScreenWrapper
import nocamelstyle.cuber.timeryou.ui.theme.CuberTimerYouTheme

class MainActivity : ComponentActivity() {

    companion object {
        var contextTmp: Context? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contextTmp = this
        setContent {
            CuberTimerYouTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TimerScreenWrapper()
                }
            }
        }
    }
}