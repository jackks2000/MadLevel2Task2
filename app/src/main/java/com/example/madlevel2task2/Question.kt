package com.example.madlevel2task2

import androidx.recyclerview.widget.ItemTouchHelper

data class Question (
    var statement: String,
    var answer: Int
){
    companion object{
        private const val correct = ItemTouchHelper.LEFT
        private const val incorrect = ItemTouchHelper.RIGHT

        val QUESTION_STATEMENTS: Array<Question> = arrayOf(
            Question("A 'val' and 'var' are the same", incorrect),
            Question("Mobile Application Development grants 12 ECTS", incorrect),
            Question("A Unit in Kotlin corresponds to a void in Java", correct),
            Question("In Kotlin 'when' replaces 'switch' in Java", correct)
        )
//
//        val QUESTION_ANSWERS = arrayOf(
//            false,
//            true,
//            true,
//            false
//        )

    }
}
