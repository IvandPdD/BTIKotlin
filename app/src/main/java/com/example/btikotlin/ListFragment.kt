package com.example.btikotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.searchRecycler)
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = SearchAdapter()
        recycler.adapter = adapter

        adapter.onItemClickListener = {
            Toast.makeText(requireActivity(), it.name, Toast.LENGTH_SHORT).show()
        }

        val foodArray = arrayOf("Potasio25mg", "Calorías 100mg", "Proteínas 200mg", "Hidratos de Carbono 67g")

        val foodList = mutableListOf(
            Food("Manzana", foodArray),
            Food("Melón", foodArray),
            Food("Sandía",foodArray),
            Food("Chocolate",foodArray),
            Food("Plátano",foodArray),
            Food("Lentejas",foodArray),
            Food("Pollo",foodArray),
            Food("Granada",foodArray),
            Food("Patata",foodArray),
            Food("Huevo",foodArray),
            Food("Jamón",foodArray),
            Food("Bacalao",foodArray),
        )

        adapter.submitList(foodList)

        return view
    }
}