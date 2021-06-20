package com.example.firefly.Adapter

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.firefly.ListFragmentDirections
import com.example.firefly.MyViewModel
import com.example.firefly.database.Bug
import com.example.firefly.databinding.ItemLayoutBinding

class BugAdapter (val view: Context, val viewModel: MyViewModel): ListAdapter<Bug, BugAdapter.ViewHolder>(BallDiffCallback()), SwipeHandlerInterface{
    class BallDiffCallback : DiffUtil.ItemCallback<Bug>() {
        override fun areItemsTheSame(oldItem: Bug, newItem: Bug): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Bug, newItem:Bug): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(layoutInflater, parent, false)
        val viewHolder = ViewHolder(binding)
        viewHolder.itemView.setOnClickListener {
            //get the selected scene's id in the database
            val rawId = getItem(viewHolder.bindingAdapterPosition).id
            //pass the id to the detailfragment
            it.findNavController()
                .navigate(ListFragmentDirections.actionListFragmentToDetailFragment(rawId))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bug = getItem(position)
        holder.binding.bug = bug
        holder.binding.executePendingBindings()
    }

    override fun onItemDelete(position: Int){
        val deleteBug = getItem(position)
        AlertDialog.Builder(view).apply {
            setTitle("Delete this data?")
            setCancelable(false)
            setPositiveButton("Yes"){
                    dialog, which -> viewModel.deleteBug(deleteBug)
            }
            setNegativeButton("No"){
                    dialog, which -> notifyItemChanged(position)
            }
            show()
        }
    }
}

@BindingAdapter("setImage")
fun ImageView.setSceneImage(bug: Bug?) {
    bug?.let {
        if (bug.photoFile.isNotEmpty()) {
            Glide.with(this.context)
                .load(Uri.parse(bug.photoFile))
                .apply(RequestOptions().centerCrop())
                .into(this)
        } else {
            setImageResource(bug.photoId)
        }
    }
}