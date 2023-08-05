package nocamelstyle.cuber.timeryou.dataset

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Immutable
@Entity
data class Record(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val time: Int,
    val penalty: String,
    val cubeType: String,
    val cubeCategory: String,
    val saveDate: Long
)