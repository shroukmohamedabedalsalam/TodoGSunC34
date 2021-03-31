package com.route.todoappgsun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.route.base.BaseActivity
import com.route.todoappgsun.database.TasksDataBase
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {
    lateinit var adapter: TasksAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        add_btn.setOnClickListener{
           openAddTaskActivity()
        }

    }





    private fun initRecyclerView() {
        adapter = TasksAdapter(listOf())
        recycler_view.adapter = adapter
        var SimpleCallback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT
        )

        {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
               return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val Position = viewHolder.adapterPosition
                val task =  adapter.gettaskbypositon(position = Position)
                TasksDataBase.getInstance(applicationContext)
                    .tasksDao()
                    .deleteTask(task)
                reload()

            }
        }
        var   itemTouchHelper: ItemTouchHelper= ItemTouchHelper(SimpleCallback)
        itemTouchHelper.attachToRecyclerView(recycler_view)


    }
    fun reload(){
        val tasks=   TasksDataBase.getInstance(applicationContext)
            .tasksDao()
            .getAllTasks()
        adapter.changeData(tasks )
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
        val tasks =
            TasksDataBase.getInstance(applicationContext)
                .tasksDao()
                .getAllTasks()
        adapter.changeData(tasks)

    }

    private fun openAddTaskActivity() {
        val intent = Intent(this,AddTaskActivity::class.java)
        startActivity(intent)
    }
}