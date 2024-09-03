package br.edu.ifsp.ea_2.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.ea_2.R
import br.edu.ifsp.ea_2.data.repository.DiaryRepository
import br.edu.ifsp.ea_2.databinding.ActivityMainBinding
import br.edu.ifsp.ea_2.ui.adapter.DiaryEntryAdapter
import br.edu.ifsp.ea_2.ui.diaryadd.DiaryAddActivity
import br.edu.ifsp.ea_2.ui.listeners.DiaryEntryItemClickListener
import br.edu.ifsp.ea_2.ui.main.viewmodel.MainViewModel
import br.edu.ifsp.ea_2.ui.main.viewmodel.MainViewModelFactory
import br.edu.ifsp.ea_2.util.Constant

class MainActivity : AppCompatActivity(), DiaryEntryItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = DiaryEntryAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = MainViewModelFactory(DiaryRepository(applicationContext))
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        setupRecyclerView()
        setupObservers()
        setupListeners()
    }
    override fun onResume() {
        super.onResume()
        viewModel.checkDatabase()
    }

    override fun clickOpen(position: Long) {
        val mIntent = Intent(this, DiaryAddActivity::class.java)
        mIntent.putExtra(Constant.DIARY_ENTRY_ID, position)
        startActivity(mIntent)
    }

    override fun clickDelete(position: Long) {
        viewModel.deleteDiaryEntry(position)
    }

    private fun setupListeners() {
        binding.buttomAdicionar.setOnClickListener{
            val mIntent = Intent(this, DiaryAddActivity::class.java)
            startActivity(mIntent)
        }
    }
    private fun setupRecyclerView() {
        binding.reciclerviewDiaryEntry.layoutManager = LinearLayoutManager(this)
        binding.reciclerviewDiaryEntry.adapter = adapter
    }
    private fun setupObservers() {
        viewModel.tasks.observe(this, Observer {
            adapter.submitDataset(it)
        })
    }
}

