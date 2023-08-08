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

    @Query("SELECT * FROM record WHERE cubeType = :cubeType AND cubeCategory = :cubeCategory  ORDER BY saveDate")
    fun getBy(cubeType: String, cubeCategory: String): List<Record>

    @Insert
    fun insert(record: Record)

    @Delete
    fun delete(record: Record)
}