package br.edu.ifsp.ea_2.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.ea_2.data.repository.DiaryRepository

class MainViewModelFactory(
    private val repository: DiaryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("View Model desconhecido!")
    }
}
