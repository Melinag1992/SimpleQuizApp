package com.example.quizapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentQuestionBinding
import com.example.quizapp.domain.QuestionnaireViewModel
import com.example.quizapp.domain.models.Question


class QuestionFragment : Fragment(R.layout.fragment_question) {
    private lateinit var viewBinding: FragmentQuestionBinding
    private lateinit var viewModel: QuestionnaireViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentQuestionBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this.requireActivity())[QuestionnaireViewModel::class.java]

        viewModel.getQuestionData().observe(viewLifecycleOwner, Observer { question->
            validateSelected()
            populateViews(question)
        })
        return viewBinding.root
    }


    private fun validateSelected(){
        var selected = ""
        viewBinding.radioGrp.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                viewBinding.radioBtn1.id -> selected = viewBinding.radioBtn1.text.toString()
                viewBinding.radioBtn2.id -> selected = viewBinding.radioBtn2.text.toString()
                viewBinding.radioBtn3.id -> selected = viewBinding.radioBtn3.text.toString()
                viewBinding.radioBtn4.id -> selected = viewBinding.radioBtn4.text.toString()
            }
            viewModel.setSelectedAnswer(selected)
        }

    }

    private fun populateViews(question: Question){
        viewBinding.radioGrp.clearCheck()

        viewBinding.questionTv.text = question.title
        viewBinding.radioBtn1.text = question.options[0]
        viewBinding.radioBtn2.text = question.options[1]
        viewBinding.radioBtn3.text = question.options[2]
        viewBinding.radioBtn4.text = question.options[3]
    }

}