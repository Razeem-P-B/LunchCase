package lunchcase.com.lunchcase.Abstract


import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import lunchcase.com.lunchcase.Data.AddPrice
import lunchcase.com.lunchcase.Data.AddUser
import lunchcase.com.lunchcase.Interface.Customer


@Database(entities = arrayOf(AddUser::class, AddPrice::class), version = 1)
abstract class LunchCaseDatabase : RoomDatabase() {
    abstract fun customerDataDao(): Customer

    companion object {
        private var INSTANCE: LunchCaseDatabase? = null

        fun getInstance(context: Context): LunchCaseDatabase? {
            if (INSTANCE == null) {
                synchronized(LunchCaseDatabase::class) {
                    INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                            LunchCaseDatabase::class.java).allowMainThreadQueries().build()

                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
