package lunchcase.com.lunchcase.Data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "AddPrice")
data class AddPrice(@PrimaryKey var id: Long?, @ColumnInfo(name = "SingleTime") var singleTime: String, @ColumnInfo(name = "TwoTime") var twoTime: String, @ColumnInfo(name = "threeTime") var threeTime: String) {
    constructor() : this(null, "", "", "")
}