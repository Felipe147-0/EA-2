package br.edu.ifsp.ea_2.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.ea_2.R
import br.edu.ifsp.ea_2.databinding.ActivityMainBinding
import br.edu.ifsp.ea_2.ui.adapter.DiaryEntryAdapter
import br.edu.ifsp.ea_2.ui.listeners.DiaryEntryItemClickListener

class MainActivity : AppCompatActivity(), DiaryEntryItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = DiaryEntryAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = MainViewModelFactory(TaskRepository(applicationContext))
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        setupRecyclerView()
        setupObservers()
        setupListeners()
    }
    override fun onResume() {
        super.onResume()
        viewModel.checkDatabase()
    }
    override fun clickDone(position: Int) {
        val task = adapter.getDatasetItem(position)
        viewModel.makeTaskDone(task.id)
    }
    override fun clickOpen(position: Int) {
        val task = adapter.getDatasetItem(position)
        val mIntent = Intent(this, DetailsActivity::class.java)
        mIntent.putExtra(Constant.TASK_ID, task.id)
        startActivity(mIntent)
    }
    private fun setupListeners() {
        binding.buttonAdd.setOnClickListener{
            val mIntent = Intent(this, DetailsActivity::class.java)
            startActivity(mIntent)
        }
    }
    private fun setupRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter
    }
    private fun setupObservers() {
        viewModel.tasks.observe(this, Observer {
            adapter.submitDataset(it)
        })
    }
}

