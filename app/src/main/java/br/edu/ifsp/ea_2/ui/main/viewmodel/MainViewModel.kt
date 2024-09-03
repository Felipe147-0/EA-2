package br.edu.ifsp.ea_2.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.ea_2.data.model.DiaryEntry
import br.edu.ifsp.ea_2.data.repository.DiaryRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DiaryRepository) : ViewModel() {
    private val _diaryEntry = MutableLiveData<List<DiaryEntry>>()
    val tasks: LiveData<List<DiaryEntry>> = _diaryEntry

    init {
        checkDatabase()
    }

    fun checkDatabase() {
        viewModelScope.launch {
            val list = repository.findAll()
            _diaryEntry.postValue(list)
        }
    }

    fun deleteDiaryEntry(id: Long) {
        viewModelScope.launch {
            val diaryEntry = repository.findById(id)
            if (diaryEntry != null) {
                repository.remove(diaryEntry)
                checkDatabase()
            }
        }
    }
}