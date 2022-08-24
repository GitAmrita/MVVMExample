package com.example.mvvmExample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmExample.model.Blog
import com.example.mvvmExample.viewModel.BlogViewModel
import com.example.mvvmExample.databinding.ItemBinding


class BlogAdapter(
    val viewModel: BlogViewModel,
    private val arrayList: ArrayList<Blog>) : RecyclerView.Adapter<BlogAdapter.BlogViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
      val binding = ItemBinding.inflate(
          LayoutInflater.from(parent.context), parent, false)
        return BlogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class BlogViewHolder(
        private val binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(blog: Blog) {
            val txtBox = binding.title
            val deleteBtn = binding.delete
            txtBox.text = blog.title
            deleteBtn.setOnClickListener {
                viewModel.remove(blog)
            }
        }
    }
}