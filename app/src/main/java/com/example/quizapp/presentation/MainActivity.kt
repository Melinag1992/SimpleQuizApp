package com.example.quizapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment

import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.domain.QuestionaireViewModel
import com.example.quizapp.presentation.fragments.CongratsFragment
import com.example.quizapp.presentation.fragments.QuestionFragment

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityMainBinding
    private val viewModel: QuestionaireViewModel by viewModels() // when the activity is killed or recreated we pull our viewmodel from current state

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                replaceFragment(QuestionFragment())
            }


            viewBinding.questionSubmitBtns.setOnClickListener {
                viewModel.updateQuestion()
             }

            viewModel.getTotalScore().observe(this, Observer { totalScoreText ->
                viewBinding.tvFinalScore.text = totalScoreText

            })

            viewModel.getIsCompleted().observe(this, Observer { isComplete->
                if(isComplete){
                    replaceFragment(CongratsFragment())
                    viewBinding.tvFinalScore.visibility = View.GONE
                    viewBinding.questionSubmitBtns.visibility = View.GONE
                }

            })


            viewModel.getIsReset().observe(this, Observer {isReset->
            if(isReset){
                replaceFragment(QuestionFragment())
                viewModel.resetValues()
                viewBinding.questionSubmitBtns.visibility = View.VISIBLE
                viewBinding.tvFinalScore.visibility = View.VISIBLE
                }
            })

      }

    }



    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.commit {
        setReorderingAllowed(true)
        replace(R.id.fragment_container, fragment).addToBackStack("Congrats")
        }
    }


}