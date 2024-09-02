package br.edu.ifsp.ea_2.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.ea_2.R
import br.edu.ifsp.ea_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

    }
}