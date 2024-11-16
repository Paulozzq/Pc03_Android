package com.garcia.paulo.laboratoriocalificado03

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.garcia.paulo.laboratoriocalificado03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ProfeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvProfeList.layoutManager = LinearLayoutManager(this)
        adapter = ProfeAdapter(emptyList())
        binding.rvProfeList.adapter = adapter

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.profeList.observe(this, Observer { profeList ->
            profeList?.let {
                adapter.list = it
                adapter.notifyDataSetChanged()
            }
        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
        })

        viewModel.errorApi.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })
    }
}
