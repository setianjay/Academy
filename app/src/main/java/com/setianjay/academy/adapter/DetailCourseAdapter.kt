package com.setianjay.academy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setianjay.academy.data.ModuleEntity
import com.setianjay.academy.databinding.ItemsModuleListBinding

class DetailCourseAdapter: RecyclerView.Adapter<DetailCourseAdapter.ViewHolder>() {

    private val listModules: ArrayList<ModuleEntity> = ArrayList()

    fun setModules(modules: List<ModuleEntity>){
        this.listModules.clear()
        this.listModules.addAll(modules)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemsModuleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modules = listModules[position]
        holder.bind(modules)
    }

    override fun getItemCount(): Int {
        return listModules.size
    }

    inner class ViewHolder(private val binding: ItemsModuleListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(module: ModuleEntity){
            binding.textModuleTitle.text = module.title
        }
    }

}