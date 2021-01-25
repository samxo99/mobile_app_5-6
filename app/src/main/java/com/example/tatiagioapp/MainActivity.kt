package com.example.tatiagioapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var mDatabase: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDatabase = FirebaseDatabase.getInstance().reference
        val addButton = findViewById<View>(R.id.fab_add_button) as FloatingActionButton
        addButton.setOnClickListener {
            addNewItemDialog()
        }

        auth = Firebase.auth
    }

    private fun addNewItemDialog(){
        val alert = AlertDialog.Builder(this)
        val itemEditTxt = EditText(this)
        alert.setTitle("Add new item: ")
        alert.setView(itemEditTxt)
        alert.setPositiveButton("Add"){ dialog, positiveButton ->
            val todoItem = ToDoItem.create()
            todoItem.itemTitle = itemEditTxt.text.toString()
            todoItem.isDone = false
            val newItem = mDatabase.child(Constants.FIREBASE_ITEM).push()
            todoItem.itemID = newItem.key
            newItem.setValue(todoItem)

            dialog.dismiss()

            Toast.makeText(this, "Item saved with ID: " + todoItem.itemID, Toast.LENGTH_SHORT).show()
        }
        alert.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if( item.itemId == R.id.menuItemLogOut) {
            Log.d("MainActivity", "Logout")
            auth.signOut()

            val logOutIntent = Intent(this, LoginActivity::class.java);
            logOutIntent.flags =  Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logOutIntent)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

}