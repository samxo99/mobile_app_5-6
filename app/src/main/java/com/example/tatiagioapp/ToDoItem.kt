package com.example.tatiagioapp

class ToDoItem{
    companion object Factory{
        fun create(): ToDoItem = ToDoItem()
    }

    var itemID: String? = null
    var itemTitle: String? = null
    var isDone: Boolean? = false
}