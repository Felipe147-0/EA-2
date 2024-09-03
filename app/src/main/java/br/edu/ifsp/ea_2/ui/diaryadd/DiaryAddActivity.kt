package br.edu.ifsp.ea_2.ui.diaryadd

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.ea_2.R
import br.edu.ifsp.ea_2.data.repository.DiaryRepository
import br.edu.ifsp.ea_2.databinding.ActivityDiaryAddBinding
import br.edu.ifsp.ea_2.ui.diaryadd.viewmodel.DiaryAddViewModel
import br.edu.ifsp.ea_2.ui.diaryadd.viewmodel.DiaryAddViewModelFactory
import br.edu.ifsp.ea_2.util.Constant

class DiaryAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiaryAddBinding
    private lateinit var viewModel: DiaryAddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = DiaryAddViewModelFactory(DiaryRepository(applicationContext))
        viewModel = ViewModelProvider(this, factory).get(DiaryAddViewModel::class.java)
        handleBundle()
        setupListeners()
        setupObservers()
    }

    private fun handleBundle(){
        if (intent.hasExtra(Constant.DIARY_ENTRY_ID)){
            val id = intent.getLongExtra(Constant.DIARY_ENTRY_ID, -1)
            viewModel.showEvent(id)
        }
    }

    private fun setupObservers() {
        viewModel.saved.observe(this, Observer {
            if (it){
                Toast.makeText(this, "Informação com sucesso.",
                    Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Erro ao salvar a informação.", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.isUpdate.observe(this, Observer {
            if (it){
                binding.buttonSave.text = "salvar a alteração"
            }
        })
        viewModel.title.observe(this, Observer {
            binding.edittextTitleField.setText(it)
        })
        viewModel.note.observe(this, Observer {
            binding.edittextNoteField.setText(it)
        })
        viewModel.location.observe(this, Observer {
            binding.edittextLocationField.setText(it)
        })
        viewModel.date.observe(this, Observer {
            binding.edittextDateField.setText(it)
        })
        viewModel.time.observe(this, Observer {
            binding.edittextTimeField.setText(it)
        })
    }
    private fun setupListeners() {

        binding.buttonSave.setOnClickListener {
            val title = binding.edittextTitleField.text.toString()
            val note = binding.edittextNoteField.text.toString()
            val location = binding.edittextLocationField.text.toString()
            val date = binding.edittextDateField.text.toString()
            val time = binding.edittextTimeField.text.toString()

            if (title.isEmpty() || title.isBlank()){
                Toast.makeText(this, "Título é necessario",
                    Toast.LENGTH_SHORT).show()
            } else if (date.isEmpty() || date.isBlank()) {
                Toast.makeText(this, "Data é necessario",
                    Toast.LENGTH_SHORT).show()

            } else {
                viewModel.insert(title, note, location, date, time)
            }
        }
    }

}
