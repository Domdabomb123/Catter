package com.dschumerth.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dschumerth.abstractions.CatPhoto

@Dao
abstract class CatDao {

    @Query("SELECT * FROM cat_photos ORDER BY id DESC")
    abstract suspend fun getCatsFromDB(): List<CatPhoto>

    @Query("SELECT * FROM cat_photos WHERE id = :catId")
    abstract suspend fun getCatFromDB(catId: Int): List<CatPhoto>

    @Insert
    abstract suspend fun insertCatInDB(cat: CatPhoto?)

    @Delete
    abstract suspend fun deleteCatInDB(cat: CatPhoto?)

    @Query("DELETE FROM cat_photos")
    abstract fun deleteAllInDB()
}
