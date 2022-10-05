package fastcampus.aop.part2.cal

import androidx.room.Database
import androidx.room.RoomDatabase
import fastcampus.aop.part2.cal.dao.HistoryDao
import fastcampus.aop.part2.cal.model.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun historyDao(): HistoryDao
}