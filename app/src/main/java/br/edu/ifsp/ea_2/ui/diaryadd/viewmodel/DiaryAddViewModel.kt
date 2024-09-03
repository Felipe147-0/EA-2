package br.edu.ifsp.ea_2.ui.diaryadd.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.ea_2.data.model.DiaryEntry
import br.edu.ifsp.ea_2.data.repository.DiaryRepository
import kotlinx.coroutines.launch

class DiaryAddViewModel (private val repository: DiaryRepository): ViewModel() {
    private var diaryEntryId: Long = -1

    private val _saved = MutableLiveData<Boolean>()
    val saved: LiveData<Boolean> = _saved

    private val _isUpdate = MutableLiveData<Boolean>()
    val isUpdate: LiveData<Boolean> = _isUpdate

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _note = MutableLiveData<String>()
    val note: LiveData<String> = _note

    private val _location = MutableLiveData<String>()
    val location: LiveData<String> = _location

    private val _date= MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _time = MutableLiveData<String>()
    val time: LiveData<String> = _time

    init {
        _isUpdate.value = false
    }
    fun insert (title: String, note: String, location: String, date: String, time: String ){
        val diaryEntry = DiaryEntry (title = title , note = note, location = location, date = date, time = time)
        if (_isUpdate.value == false) {
            viewModelScope.launch {
                _saved.value = repository.insert(diaryEntry)
            }
        } else {
            diaryEntry.id = diaryEntryId
            viewModelScope.launch {
                _saved.value = repository.update(diaryEntry)
                diaryEntryId = -1
            }
        }
    }

    fun showEvent(id: Long) {
        viewModelScope.launch {
            val diaryEntry = repository.findById(id)
            if (diaryEntry != null){
                diaryEntryId = diaryEntry.id
                _isUpdate.value = true
                _title.value = diaryEntry.title
                _note.value = diaryEntry.note
                _location.value = diaryEntry.location
                _date.value = diaryEntry.date
                _time.value = diaryEntry.time
            }
        }
    }
}

