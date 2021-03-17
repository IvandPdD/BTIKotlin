package com.example.btikotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {

    val foodArray = arrayOf("Potasio25mg", "Calorías 100mg", "Proteínas 200mg", "Hidratos de Carbono 67g")

    var foodList:MutableList<Food> = ArrayList()
    var displayList:MutableList<Food> = ArrayList()

    var adapter = SearchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        loadData()

        val recycler = view.findViewById<RecyclerView>(R.id.searchRecycler)
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        recycler.adapter = adapter

        adapter.onItemClickListener = {
            Toast.makeText(requireActivity(), it.name, Toast.LENGTH_SHORT).show()
        }

        adapter.submitList(displayList)

        val searchBar  = view.findViewById<SearchView>(R.id.searchView)
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText!!.isNotEmpty()){
                    displayList.clear()
                    val search = newText.toLowerCase()
                    foodList.forEach{
                        if (it.name.toLowerCase().contains(search)){
                            displayList.add(it)
                        }
                    }
                    adapter.notifyDataSetChanged()
                }else{
                    displayList.clear()
                    adapter.notifyDataSetChanged()
                }

                return true
            }

        })

        return view
    }

    fun loadData() {
        foodList.add(Food("Manzana", foodArray))
        foodList.add(Food("Melón", foodArray))
        foodList.add(Food("Sandía", foodArray))
        foodList.add(Food("Chocolate", foodArray))
        foodList.add(Food("Plátano", foodArray))
        foodList.add(Food("Lentejas", foodArray))
        foodList.add(Food("Pollo", foodArray))
        foodList.add(Food("Granada", foodArray))
        foodList.add(Food("Patata", foodArray))
        foodList.add(Food("Huevo", foodArray))
        foodList.add(Food("Jamón", foodArray))
        foodList.add(Food("Bacalao", foodArray))
    }
}