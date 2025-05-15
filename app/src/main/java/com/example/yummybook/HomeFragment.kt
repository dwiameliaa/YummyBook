package com.example.yummybook

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.GridView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yummybook.network.ApiClient
import com.google.android.material.search.SearchBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ResepAdapter

    private var isKategoriVisible = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val btnKategori = view.findViewById<Button>(R.id.btnKategori)
        val layoutKategori = view.findViewById<GridLayout>(R.id.layoutKategori)

        val btnBeef = view.findViewById<Button>(R.id.btnBeef)
        val btnBreakfast = view.findViewById<Button>(R.id.btnBreakfast)
        val btnChicken = view.findViewById<Button>(R.id.btnChicken)
        val btnDessert = view.findViewById<Button>(R.id.btnDessert)
        val btnGoat = view.findViewById<Button>(R.id.btnGoat)
        val btnLamb = view.findViewById<Button>(R.id.btnLamb)
        val btnMisc = view.findViewById<Button>(R.id.btnMisc)
        val btnPasta = view.findViewById<Button>(R.id.btnPasta)
        val btnPork = view.findViewById<Button>(R.id.btnPork)
        val btnSeafood = view.findViewById<Button>(R.id.btnSeafood)
        val btnSide = view.findViewById<Button>(R.id.btnSide)
        val btnStarter = view.findViewById<Button>(R.id.btnStarter)
        val btnVegan = view.findViewById<Button>(R.id.btnVegan)
        val btnVegetarian = view.findViewById<Button>(R.id.btnVegetarian)


        // Toggle visibility layout kategori
        btnKategori.setOnClickListener {
            isKategoriVisible = !isKategoriVisible
            layoutKategori.visibility = if (isKategoriVisible) View.VISIBLE else View.GONE
        }

        btnBeef.setOnClickListener { fetchMeals("Beef") }
        btnBreakfast.setOnClickListener { fetchMeals("Breakfast") }
        btnChicken.setOnClickListener { fetchMeals("Chicken") }
        btnDessert.setOnClickListener { fetchMeals("Dessert") }
        btnGoat.setOnClickListener { fetchMeals("Goat") }
        btnLamb.setOnClickListener { fetchMeals("Lamb") }
        btnMisc.setOnClickListener { fetchMeals("Miscellaneous") }
        btnPasta.setOnClickListener { fetchMeals("Pasta") }
        btnPork.setOnClickListener { fetchMeals("Pork") }
        btnSeafood.setOnClickListener { fetchMeals("Seafood") }
        btnSide.setOnClickListener { fetchMeals("Side") }
        btnStarter.setOnClickListener { fetchMeals("Starter") }
        btnVegan.setOnClickListener { fetchMeals("Vegan") }
        btnVegetarian.setOnClickListener { fetchMeals("Vegetarian") }

        recyclerView = view.findViewById(R.id.rvResep)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Load awal
        fetchMeals("Seafood")


        return view
    }

    private fun fetchMeals(category: String) {
        ApiClient.instance.getMealsByCategory(category)
            .enqueue(object : Callback<MealResponse> {
                override fun onResponse(
                    call: Call<MealResponse>,
                    response: Response<MealResponse>
                ) {
                    if (response.isSuccessful) {
                        val meals = response.body()?.meals ?: emptyList()
                        adapter = ResepAdapter(meals)
                        recyclerView.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                    Log.e("HomeFragment", "API call failed: ${t.message}")
                }
            })
    }
}
