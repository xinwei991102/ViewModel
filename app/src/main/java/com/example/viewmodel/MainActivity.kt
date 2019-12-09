package com.example.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.viewmodel.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //Declare an instant of ViewModel
    private lateinit var viewModel: CounterViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //setContentView(R.layout.activity_main)

        //Initialise ViewModel
        viewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)

        //Add observer
        viewModel.counter.observe(this,
            Observer<Int>{
                if (viewModel.counter.value == 10) endGame()
            }
        )

        binding.apply {
            textViewCounter.text = viewModel.counter.value.toString()
        }

        buttonPlus.setOnClickListener {
            viewModel.increment()
            binding.apply {
                textViewCounter.text = viewModel.counter.value.toString()
            }
        }

        buttonMinus.setOnClickListener {
            viewModel.decrement()
            binding.apply {
                textViewCounter.text = viewModel.counter.value.toString()
            }
        }
    }

    private fun endGame() {
        Toast.makeText(this, "Hi Jing Wen", Toast.LENGTH_LONG).show()
    }

}
