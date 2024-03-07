package com.example.quizapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.databinding.FragmentCongratsBinding
import com.example.quizapp.domain.QuestionaireViewModel

class CongratsFragment : Fragment() {
    lateinit var viewBinding : FragmentCongratsBinding
    lateinit var viewModel : QuestionaireViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentCongratsBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this.requireActivity())[QuestionaireViewModel::class.java]


        viewModel.getTotalScore().observe(viewLifecycleOwner, Observer {totalScoreText->

            viewBinding.tvScore.text = totalScoreText

        })


        viewBinding.button.setOnClickListener {
                    viewModel.setIsReset()
        }
        return viewBinding.root
    }


}
