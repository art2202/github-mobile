package com.example.desafioradix.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.desafioradix.MyApplication
import com.example.desafioradix.database.dao.DetalhesRepositorioDao
import com.example.desafioradix.database.dao.RepositorioDao
import com.example.desafioradix.database.entity.*


@Database(
    entities = [
        DetalheRepositorioEntity::class,
        RepositorioEntity::class
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun repositorioDao() : RepositorioDao
    abstract fun detalhesRepositorioDao() : DetalhesRepositorioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase (): AppDatabase? {
            if (this.INSTANCE != null) {
                return this.INSTANCE
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        MyApplication.instance,
                        AppDatabase::class.java,
                        "mobile-challenger")
                        .build()
                    this.INSTANCE = instance
                    return instance
                }
            }
        }
    }
}