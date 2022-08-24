package com.example.mvvmExample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmExample.databinding.ActivityMainBinding
import com.example.mvvmExample.model.Blog
import com.example.mvvmExample.viewModel.BlogViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val blogViewModel : BlogViewModel by lazy {
        ViewModelProvider(this)[BlogViewModel::class.java]
    }
    private  val recyclerView: RecyclerView by lazy { binding.recycler }
    private val input: TextView by lazy { binding.titletxt }
    private lateinit var submit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        submit = binding.button
        submit.setOnClickListener{
            addData(input.text.toString())
            input.text = ""
        }
        initializeBlogRecyclerView()
    }

    private fun initializeBlogRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        observeBlogData()
    }

    private fun addData(title: String) {
        val blog = Blog(title)
        blogViewModel.add(blog)
    }

    private fun observeBlogData() {
        blogViewModel.blogList.observe(this, Observer{
            Log.i("data",it.toString())
            recyclerView.adapter = BlogAdapter(blogViewModel, it.blogs)
            if (it.isAdded) {
                recyclerView.adapter?.notifyItemInserted(it.index)
            } else {
                recyclerView.adapter?.notifyItemRemoved(it.index)
            }
        })
        }
    }
