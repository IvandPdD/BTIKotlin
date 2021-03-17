package com.example.btikotlin

import android.content.Context
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

    val manzanaArray = arrayOf("200g", "Potasio25mg", "Calorías 100mg", "Proteínas 200mg", "Hidratos de Carbono 67g")
    val melonArray = arrayOf("500g", "Potasio 40mg", "Calorías 50mg", "Proteínas 30mg", "Hidratos de Carbono 80g")
    val sandiaArray = arrayOf("100g", "Potasio 2mg", "Calorías 90mg", "Proteínas 50mg", "Hidratos de Carbono 34g")
    val chocolateArray = arrayOf("75g", "Potasio 87mg", "Calorías 12mg", "Proteínas 34mg", "Hidratos de Carbono 89g")
    val platanoArray = arrayOf("50g", "Potasio 78mg", "Calorías 12mg", "Proteínas 5mg", "Hidratos de Carbono 23g")
    val lentejasArray = arrayOf("120g", "Potasio 84mg", "Calorías 13mg", "Proteínas 677mg", "Hidratos de Carbono 900g")
    val polloArray = arrayOf("10g", "Potasio 78mg", "Calorías 24mg", "Proteínas 567mg", "Hidratos de Carbono 134g")
    val granadaArray = arrayOf("23g", "Potasio 89mg", "Calorías 12mg", "Proteínas 455mg", "Hidratos de Carbono 123g")
    val patataArray = arrayOf("24g", "Potasio 2mg", "Calorías 766mg", "Proteínas 444mg", "Hidratos de Carbono 90g")
    val huevoArray = arrayOf("600g", "Potasio 1mg", "Calorías 22mg", "Proteínas 33mg", "Hidratos de Carbono 55g")
    val jamonArray = arrayOf("500g", "Potasio 500mg", "Calorías 150mg", "Proteínas 244mg", "Hidratos de Carbono 56g")
    val bacalaoArray = arrayOf("130g", "Potasio 122mg", "Calorías 133mg", "Proteínas 44mg", "Hidratos de Carbono 80g")

    var foodList:MutableList<Food> = ArrayList()
    var displayList:MutableList<Food> = ArrayList()

    var adapter = SearchAdapter()

    interface FoodSelectListener {
        fun onFoodSelected(food: Food)
    }

    private lateinit var foodSelectListener: FoodSelectListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        foodSelectListener = try {
            context as FoodSelectListener
        } catch (e: ClassCastException) {
            throw java.lang.ClassCastException("$context must implement FoodSelectListener")
        }
    }

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
            foodSelectListener.onFoodSelected(it)
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
        foodList.add(Food("Manzana", manzanaArray))
        foodList.add(Food("Melón", melonArray))
        foodList.add(Food("Sandía", sandiaArray))
        foodList.add(Food("Chocolate", chocolateArray))
        foodList.add(Food("Plátano", platanoArray))
        foodList.add(Food("Lentejas", lentejasArray))
        foodList.add(Food("Pollo", polloArray))
        foodList.add(Food("Granada", granadaArray))
        foodList.add(Food("Patata", patataArray))
        foodList.add(Food("Huevo", huevoArray))
        foodList.add(Food("Jamón", jamonArray))
        foodList.add(Food("Bacalao", bacalaoArray))
    }
}