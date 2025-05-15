package com.example.yummybook

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.yummybook.network.ApiClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailResepFragment : Fragment() {

    private lateinit var tvTitle: TextView
    private lateinit var tvInstructions: TextView
    private lateinit var ivMeal: ImageView
    private lateinit var tvIngredients: TextView

    private var mealId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mealId = arguments?.getString("MEAL_ID")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_resep, container, false)

        tvTitle = view.findViewById(R.id.tvTitle)
        tvInstructions = view.findViewById(R.id.tvInstructions)
        ivMeal = view.findViewById(R.id.ivMeal)
        tvIngredients = view.findViewById(R.id.tvIngredients)

        mealId?.let { fetchDetail(it) }

        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav?.visibility = View.GONE

        // tombol kembali ke fragment pengaturan
        val ivKembali = view.findViewById<ImageView>(R.id.iv_kembali)

        ivKembali.setOnClickListener {
            parentFragmentManager.popBackStack()

            // Tampilkan lagi BottomNavigationView
            val bottomNav =
                requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNav.visibility = View.VISIBLE

        }



        return view
    }

    private fun fetchDetail(id: String) {
        ApiClient.instance.getMealDetail(id)
            .enqueue(object : Callback<MealDetailResponse> {
                override fun onResponse(call: Call<MealDetailResponse>, response: Response<MealDetailResponse>) {
                    if (response.isSuccessful) {
                        val meal = response.body()?.meals?.firstOrNull()
                        if (meal != null) {
                            Log.d("DetailResepFragment", "Detail berhasil diterima: ${meal.strMeal}")
                            tvTitle.text = meal.strMeal
                            tvInstructions.text = meal.strInstructions
                            Glide.with(requireContext()).load(meal.strMealThumb).into(ivMeal)
                            tvIngredients.text = getFormattedIngredients(meal)
                        } else {
                            Log.d("DetailResepFragment", "Response sukses tapi data meal kosong")
                        }
                    } else {
                        Log.d("DetailResepFragment", "Response gagal dengan kode: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<MealDetailResponse>, t: Throwable) {
                    Log.e("DetailResepFragment", "Gagal panggil API: ${t.message}")
                    Toast.makeText(requireContext(), "Gagal ambil detail", Toast.LENGTH_SHORT).show()
                }
            })
    }


    private fun getFormattedIngredients(mealItem: MealDetailItem): String {
        val ingredients = listOf(
            mealItem.strIngredient1,
            mealItem.strIngredient2,
            mealItem.strIngredient3,
            mealItem.strIngredient4,
            mealItem.strIngredient5,
            mealItem.strIngredient6,
            mealItem.strIngredient7,
            mealItem.strIngredient8,
            mealItem.strIngredient9,
            mealItem.strIngredient10
        )

        return ingredients
            .filter { !it.isNullOrBlank() }
            .joinToString(", ")
    }



}
