package com.dschumerth.catter.ui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dschumerth.catter.data.Repository
import com.dschumerth.catter.ui.viewmodel.CatterVM

class CatterViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CatterVM(repository) as T
    }
}
