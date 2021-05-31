package com.rsschool.android2021

import android.annotation.SuppressLint
import android.app.Activity


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.lang.NumberFormatException

class   FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private lateinit var activityInterface: Interface

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        activityInterface = activity as Interface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        // TODO: val min = ...
        // TODO: val max = ...
        val minvalue = view.findViewById<EditText>(R.id.min_value)
        val maxvalue = view.findViewById<EditText>(R.id.max_value)

        generateButton?.setOnClickListener {
            // TODO: send min and max to the SecondFragment


            try {

                val min: Int = minvalue.text.toString().toInt()
                val max: Int = maxvalue.text.toString().toInt()

                if (validValue(min, max)) {
                    activityInterface.setSecondFragment_toStart(min, max)


                } else {
                    Toast.makeText(view.context, "Не валидные данные", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }


            }
            catch (E: NumberFormatException){
                Toast.makeText(view.context, "Не валидные данные", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
        }
    }



    private fun validValue(min: Int, max: Int): Boolean {
        return min<max && min<2147483647 && max<=2147483647 && min>0 && max>0
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}