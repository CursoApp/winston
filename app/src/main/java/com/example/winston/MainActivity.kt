package com.example.winston

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var tipAdapter: TipAdapter
    private val tipList = mutableListOf<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tipAdapter = TipAdapter(tipList)
        val recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = tipAdapter

        val buttonAddTip
        buttonAddTip.setOnClickListener {
            showAddTipDialog()
        }

        val buttonCalculateTip
        buttonCalculateTip.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("TOTAL_TIPS", tipList.sum())
            startActivity(intent)
        }
    }

    private fun showAddTipDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Añadir Propina")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        builder.setView(input)

        builder.setPositiveButton("Añadir") { dialog, _ ->
            val tip = input.text.toString().toDoubleOrNull()
            if (tip != null) {
                tipAdapter.addTip(tip)
            }
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }
}

 /*       import android.os.Bundle
        import android.widget.Toast
        import androidx.recyclerview.widget.LinearLayoutManager

        class MainActivity {

            private lateinit var binding: ActivityMainBinding

            /*class ActivityMainBinding {

            }*/

            private lateinit var adapter: TaskAdapter
            private lateinit var taskList: List<Task>

            private lateinit var taskDAO: TaskDAO

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                binding = ActivityMainBinding.inflate(layoutInflater)
                setContentView(binding.root)

                taskDAO = TaskDAO(this)

                adapter = TaskAdapter(emptyList(), {
                    Toast.makeText(this, "Click en tarea: ${taskList[it].name}", Toast.LENGTH_SHORT).show()
                }, {
                    val task = taskList[it]
                    taskDAO.delete(task)
                    Toast.makeText(this, "Tarea borrada correctamente", Toast.LENGTH_SHORT).show()
                    loadData()
                }, {
                    val task = taskList[it]
                    task.done = !task.done
                    taskDAO.update(task)
                    loadData()
                })

                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(this)

                binding.addTaskButton.setOnClickListener {
                    val intent = Intent(this, TaskActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onResume() {
                super.onResume()

                loadData()
            }

            private fun loadData() {
                taskList = taskDAO.findAll()

                adapter.updateData(taskList)
            }

        }*/