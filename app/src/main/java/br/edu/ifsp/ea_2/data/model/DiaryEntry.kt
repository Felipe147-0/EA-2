package br.edu.ifsp.ea_2.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary")
data class DiaryEntry (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name ="title")
    val title: String,

    @ColumnInfo(name ="note")
    val note: String,

    @ColumnInfo(name ="location")
    val location: String,

    @ColumnInfo(name ="date")
    val date: String,

    @ColumnInfo(name ="time")
    val time: String
)