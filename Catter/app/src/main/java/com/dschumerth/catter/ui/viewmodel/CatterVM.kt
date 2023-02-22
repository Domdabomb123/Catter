package com.dschumerth.catter.ui.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dschumerth.abstractions.CatPhoto
import com.dschumerth.catter.data.Repository
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class CatterVM(private val repository: Repository) : ViewModel() {

    val catResponse: MutableLiveData<Bitmap?> = MutableLiveData()
    val catList: MutableLiveData<List<CatPhoto>?> = MutableLiveData()
    val selectedCat: MutableLiveData<CatPhoto?> = MutableLiveData()

    init {
        getImage()
    }

    fun getImage() {
        viewModelScope.launch {
            val response: ResponseBody = repository.getFromApiImage()
            catResponse.value = BitmapFactory.decodeStream(response.byteStream())
        }
    }

    fun getCatById(id: Int) {
        viewModelScope.launch {
            selectedCat.value = repository.getCatById(id)
        }
    }

    fun getDataFromRemote() {
        viewModelScope.launch {
            catList.value = repository.getRepoCats()
        }
    }

    fun insert(cat: CatPhoto?) = viewModelScope.launch {
        repository.insertInDatabase(cat)
    }

    fun delete(cat: CatPhoto?) = viewModelScope.launch {
        repository.deleteInDatabase(cat)
    }
}