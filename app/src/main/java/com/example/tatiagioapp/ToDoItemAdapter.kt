package com.example.tatiagioapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView

class ToDoItemAdapter(context: Context, toDoItemList: MutableList<ToDoItem>) : BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var itemList = toDoItemList

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemID: String = itemList.get(position).itemID as String
        val itemText: String = itemList.get(position).itemTitle as String
        val isDone: Boolean = itemList.get(position).isDone as Boolean

        val view: View
        val vh: ListRowHolder
        if(convertView == null){
            view = mInflater.inflate(R.layout.row_items, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        }else{
            view = convertView
            vh = view.tag as ListRowHolder
        }
        vh.label.text = itemText
        vh.isDone.isChecked = isDone
        return view
    }

    override fun getItem(index: Int): Any {
        return itemList.get(index)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return  itemList.size
    }


    private class ListRowHolder(row: View?){
        val label: TextView = row!!.findViewById<TextView>(R.id.tv_item_text) as TextView
        val isDone: CheckBox = row!!.findViewById<CheckBox>(R.id.cb_item_is_done) as CheckBox
        val isDeleteObject: ImageButton = row!!.findViewById<ImageButton>(R.id.iv_cross) as ImageButton
    }

}