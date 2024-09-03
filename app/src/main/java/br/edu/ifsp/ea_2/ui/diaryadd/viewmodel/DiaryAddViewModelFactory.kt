package br.edu.ifsp.ea_2.ui.diaryadd.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.ea_2.data.repository.DiaryRepository
import br.edu.ifsp.ea_2.ui.main.viewmodel.MainViewModel

class DiaryAddViewModelFactory (
    private val repository: DiaryRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DiaryAddViewModel::class.java)) {
                return DiaryAddViewModel(repository) as T
            }
            throw IllegalArgumentException("View Model desconhecido!")
        }
}