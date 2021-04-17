package com.raywenderlich.listmaker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListDetailActivity : AppCompatActivity() {
    lateinit var list:TaskList
    lateinit var listItemsRecyclerView: RecyclerView
    lateinit var addTaskButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!

        listItemsRecyclerView = findViewById(R.id.list_items_recyclerview)
        listItemsRecyclerView.adapter = ListItemsRecyclerViewAdapter(list)
        listItemsRecyclerView.layoutManager = LinearLayoutManager(this)

        addTaskButton = findViewById(R.id.floatingActionButton)
        addTaskButton.setOnClickListener{
            showCreateTaskDialog()
        }
    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY,list)

        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)

        // Can I do this to send data?
//        val intent = Intent()
//        intent.putExtra(MainActivity.INTENT_LIST_KEY,list)
//        setResult(Activity.RESULT_OK,intent)

        super.onBackPressed()
    }

    private fun showCreateTaskDialog() {
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
                .setTitle(R.string.task_to_add)
                .setView(taskEditText)
                .setPositiveButton(R.string.add_task){dialog, _ ->
                    val task = taskEditText.text.toString()
                    list.tasks.add(task)

                    val recyclerAdapter = listItemsRecyclerView.adapter
                            as ListItemsRecyclerViewAdapter
                    recyclerAdapter.notifyItemInserted(list.tasks.size-1)

                    dialog.dismiss()
        }
                .create()
                .show()
    }

}