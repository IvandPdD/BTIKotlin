package com.example.btikotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DetailFragment : Fragment() {

    private lateinit var mainInfoTV: TextView
    private lateinit var secondInfoTv: TextView
    private lateinit var foodNameTV: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_detail, container, false)

        mainInfoTV = rootView.findViewById(R.id.mainInfo)
        secondInfoTv = rootView.findViewById(R.id.secondaryInfo)
        foodNameTV = rootView.findViewById(R.id.foodNameTV)

        return rootView
    }

    fun setFoodData(food: Food){
        foodNameTV.text = food.name
        mainInfoTV.text = food.info[0]

        //APAÑO PARA LA REVIEW
        secondInfoTv.text = food.info[1] + "\n"+ food.info[2] + "\n" + food.info[3] + "\n" + food.info[4]

    }

}