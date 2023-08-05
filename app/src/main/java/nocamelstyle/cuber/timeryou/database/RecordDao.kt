package nocamelstyle.cuber.timeryou.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import nocamelstyle.cuber.timeryou.dataset.Record

@Dao
interface RecordDao {
    @Query("SELECT * FROM record ORDER BY saveDate")
    fun getAll(): List<Record>

    @Insert
    fun insert(record: Record)

    @Delete
    fun delete(record: Record)
}