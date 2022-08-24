package com.example.mvvmExample.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmExample.model.Blog

class BlogViewModel: ViewModel() {
    var blogList = MutableLiveData<BlogResults>()
    private var list = BlogResults(arrayListOf(), true, 0)

    fun add(blog: Blog) {
        list.blogs.add(blog)
        list.index = list.blogs.indexOf(blog)
        list.isAdded = true
        blogList.value = list
    }

    fun remove(blog: Blog) {
        list.index = list.blogs.indexOf(blog)
        list.blogs.remove(blog)
        list.isAdded = false
        blogList.value = list

    }
}

class BlogResults(
    var blogs: ArrayList<Blog>,
    var isAdded: Boolean,
    var index: Int
)