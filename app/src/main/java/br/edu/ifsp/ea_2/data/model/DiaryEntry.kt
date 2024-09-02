package br.edu.ifsp.ea_2.data.model


data class DiaryEntry (
    val id: Int,
    val title: String,
    val note: String,
    val location: String,
    val date: String,
    val time: String
)