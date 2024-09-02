package br.edu.ifsp.ea_2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.edu.ifsp.ea_2.data.dao.DiaryEntryDao
import br.edu.ifsp.ea_2.data.model.DiaryEntry

@Database(entities = [DiaryEntry::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        const val DATABASE_NAME = "diary"
        private lateinit var instance: AppDatabase

        fun getInstance(context: Context): AppDatabase {

            if (!::instance.isInitialized) {
                synchronized(AppDatabase::class) {
                    instance = Room
                        .databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            DATABASE_NAME
                        )
                        .build()
                }
            }
            return instance
        }
    }

    abstract fun getDiaryEntryDao(): DiaryEntryDao
}