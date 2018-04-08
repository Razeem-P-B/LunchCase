package lunchcase.com.lunchcase.Data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Adduser")
data class AddUser(@PrimaryKey(autoGenerate = true) var id: Long?, @ColumnInfo(name = "name") var name: String, @ColumnInfo(name = "phone") var phone: String, @ColumnInfo(name = "email") var email: String, @ColumnInfo(name = "address") var address: String) {
    constructor() : this(null, "", "", "", "")
}