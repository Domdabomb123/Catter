package com.dschumerth.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dschumerth.database.daos.CatDao
import com.dschumerth.database.helper.ConverterHelper

class CatDataBaseInstance {

    @Database(
        entities = [com.dschumerth.abstractions.CatPhoto::class],
        version = 1,
        exportSchema = false
    )
    @TypeConverters(ConverterHelper::class)
    abstract class CatRoomDatabase : RoomDatabase() {
        abstract fun appDao(): CatDao

        companion object {
            @Volatile
            private var INSTANCE: CatRoomDatabase? = null
            fun getDataBase(context: Context): CatRoomDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CatRoomDatabase::class.java,
                        "cat_database"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }
}
