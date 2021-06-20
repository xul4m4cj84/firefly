package com.example.firefly.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.ProtocolFamily

@Entity
data class Bug(var city: String, var family: String, var name: String, var photoId: Int, var description: String,var address: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
    var photoFile: String = ""
}