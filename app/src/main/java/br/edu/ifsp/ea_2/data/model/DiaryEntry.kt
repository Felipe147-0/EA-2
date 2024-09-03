package br.edu.ifsp.ea_2.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary")
data class DiaryEntry (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name ="title")
    var title: String,

    @ColumnInfo(name ="note")
    var note: String,

    @ColumnInfo(name ="location")
    var location: String,

    @ColumnInfo(name ="date")
    var date: String,

    @ColumnInfo(name ="time")
    var time: String
)