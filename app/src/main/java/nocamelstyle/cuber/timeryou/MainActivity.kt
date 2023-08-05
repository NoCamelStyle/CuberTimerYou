package nocamelstyle.cuber.timeryou

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import nocamelstyle.cuber.timeryou.database.AppDatabase
import nocamelstyle.cuber.timeryou.database.RecordDao
import nocamelstyle.cuber.timeryou.ui.screens.host.HostScreen
import nocamelstyle.cuber.timeryou.ui.theme.CuberTimerYouTheme

class MainActivity : ComponentActivity() {

    companion object {
        var contextTmp: Context? = null
        var recordDao: RecordDao? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contextTmp = this
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
        recordDao = db.recordDao()

        setContent {
            CuberTimerYouTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HostScreen()
                }
            }
        }
    }
}