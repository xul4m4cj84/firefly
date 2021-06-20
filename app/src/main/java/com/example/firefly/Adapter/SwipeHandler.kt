package com.example.firefly.Adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeHandler(val adapter: SwipeHandlerInterface, dragDirs : Int, swipeDirs : Int) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false  //disable move up and down
    }
    //swipe left or right
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val deletedPosition = viewHolder.bindingAdapterPosition
        //pass the position to the adapter where the data item is deleted from the database
        adapter.onItemDelete(deletedPosition)
    }
}