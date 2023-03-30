package com.dschumerth.catter.data

import androidx.annotation.WorkerThread
import com.dschumerth.abstractions.CatPhoto
import com.dschumerth.api.api.RetrofitInstance
import com.dschumerth.database.daos.CatDao
import okhttp3.ResponseBody

class Repository(private val catDao: CatDao?) {

    suspend fun getRepoCats(): List<CatPhoto>? {
        return catDao?.getCatsFromDB()
    }

    suspend fun getCatById(id: Int): CatPhoto? {
        return catDao?.getCatFromDB(id)?.first()
    }

    @WorkerThread
    suspend fun insertInDatabase(cat: CatPhoto?) {
        catDao?.insertCatInDB(cat)
    }

    @WorkerThread
    suspend fun deleteInDatabase(cat: CatPhoto?) {
        catDao?.deleteCatInDB(cat)
    }

    suspend fun getFromApiImage(): ResponseBody {
        return RetrofitInstance.API.getImage()
    }
}
