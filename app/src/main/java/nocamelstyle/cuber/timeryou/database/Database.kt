package nocamelstyle.cuber.timeryou.database

import androidx.room.Database
import androidx.room.RoomDatabase
import nocamelstyle.cuber.timeryou.dataset.Record

@Database(entities = [Record::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recordDao(): RecordDao
}