package lunchcase.com.lunchcase.Interface

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import lunchcase.com.lunchcase.Data.AddPrice
import lunchcase.com.lunchcase.Data.AddUser

@Dao
interface Customer {

    @Insert(onConflict = REPLACE)
    fun insertCustomerRecord(user: AddUser)

    @Query("SELECT * from Adduser")
    fun getAllUser(): List<AddUser>

    @Insert(onConflict = REPLACE)
    fun insertPriceRecord(price: AddPrice)
}