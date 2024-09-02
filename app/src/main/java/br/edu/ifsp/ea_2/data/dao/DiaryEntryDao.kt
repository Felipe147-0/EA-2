package br.edu.ifsp.ea_2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.ea_2.data.model.DiaryEntry


@Dao
interface DiaryEntryDao {

    @Insert
    suspend fun create(diaryEntry: DiaryEntry): Long

    @Query("SELECT * FROM diary ORDER BY date")
    suspend fun getAll(): List<DiaryEntry>

    @Update
    suspend fun update(diary: DiaryEntry): Int

    @Delete
    suspend fun delete(diary: DiaryEntry): Int

    @Query("SELECT * FROM diary WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): DiaryEntry
}