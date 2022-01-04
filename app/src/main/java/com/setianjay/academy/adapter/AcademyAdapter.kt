package com.setianjay.academy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.setianjay.academy.R
import com.setianjay.academy.data.CourseEntity
import com.setianjay.academy.databinding.ItemsAcademyBinding
import com.setianjay.academy.ui.detail.DetailCourseActivity

class AcademyAdapter: RecyclerView.Adapter<AcademyAdapter.ViewHolder>() {
    private val academyList: ArrayList<CourseEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemsAcademyBinding = ItemsAcademyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val academy = academyList[position]
        holder.bind(academy)
    }

    override fun getItemCount(): Int {
        return academyList.size
    }

    fun setAcademies(academies: List<CourseEntity>){
        this.academyList.also {
            it.clear()
            it.addAll(academies)
        }
    }

    inner class ViewHolder(private val binding: ItemsAcademyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(academy: CourseEntity) {
            with(binding) {
                tvItemTitle.text = academy.title
                tvItemDate.text =
                    itemView.resources.getString(R.string.deadline_date, academy.deadline)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailCourseActivity::class.java)
                    intent.putExtra(DetailCourseActivity.EXTRA_COURSE, academy.courseId)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(academy.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }
    }
}

