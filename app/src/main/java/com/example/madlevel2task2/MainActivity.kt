package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews(){
        binding.rvQuestions.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvQuestions.adapter = questionAdapter
        binding.rvQuestions.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        for (i in Question.QUESTION_STATEMENTS.indices) {
            questions.add(
                Question(
                    Question.QUESTION_STATEMENTS[i].statement,
                    Question.QUESTION_STATEMENTS[i].answer
                )
            )
        }
        createItemTouchHelper().attachToRecyclerView(rvQuestions)

        questionAdapter.notifyDataSetChanged()

    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        val callBack = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (questions[position].answer == direction){
                    questions.removeAt(position)
                }else{
                    Snackbar.make(
                        binding.rvQuestions[position],
                        "incorrect answer, try again",
                        Snackbar.LENGTH_SHORT).show()
                }

                questionAdapter.notifyDataSetChanged()

            }


        }


        return ItemTouchHelper(callBack)
    }



}