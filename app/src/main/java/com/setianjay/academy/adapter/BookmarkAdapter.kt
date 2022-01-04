package com.setianjay.academy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.setianjay.academy.R
import com.setianjay.academy.data.CourseEntity
import com.setianjay.academy.databinding.ItemsBookmarkBinding
import com.setianjay.academy.ui.detail.DetailCourseActivity

class BookmarkAdapter(
    private val callback: IOnBookmarkAdapter
    ) : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {
    private lateinit var binding: ItemsBookmarkBinding
    private val academies: ArrayList<CourseEntity> = ArrayList()

    fun setAcademies(academies: List<CourseEntity>) {
        this.academies.clear()
        this.academies.addAll(academies)
    }

    interface IOnBookmarkAdapter {
        fun onShareClick(academy: CourseEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemsBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val academy = this.academies[position]
        holder.bind(academy)
    }

    override fun getItemCount(): Int {
        return this.academies.size
    }

    inner class ViewHolder(
        private val binding: ItemsBookmarkBinding
    ) : RecyclerView.ViewHolder(binding.root) {
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
                imgShare.setOnClickListener { callback.onShareClick(academy) }
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