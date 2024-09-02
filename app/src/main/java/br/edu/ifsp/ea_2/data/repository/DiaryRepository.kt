package br.edu.ifsp.ea_2.data.repository

import android.content.Context
import br.edu.ifsp.ea_2.data.database.AppDatabase
import br.edu.ifsp.ea_2.data.model.DiaryEntry



class DiaryRepository (context: Context){

    private val database = AppDatabase.getInstance(context)
    private val dao = database.getDiaryEntryDao()

    suspend fun insert(diary: DiaryEntry): Boolean{
        return dao.create(diary) > 0
    }
    suspend fun update(diary: DiaryEntry): Boolean{
        return dao.update(diary) > 0
    }
    suspend fun remove(diary: DiaryEntry): Boolean{
        return dao.delete(diary) > 0
    }


}