package com.example.yummybook

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText

class EditProfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_profil, container, false)

        val ivKembali = view.findViewById<ImageView>(R.id.iv_kembali)
        val btnSimpan = view.findViewById<Button>(R.id.btn_simpan)
        val etFullname = view.findViewById<TextInputEditText>(R.id.et_fullname)
        val etUsername = view.findViewById<TextInputEditText>(R.id.et_username)
        val etEmail = view.findViewById<TextInputEditText>(R.id.et_email)

        // Sembunyikan BottomNavigationView
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.visibility = View.GONE

        ivKembali.setOnClickListener {
            parentFragmentManager.popBackStack()
            // Tampilkan BottomNavigationView lagi
            bottomNav.visibility = View.VISIBLE
        }

        // Ambil SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("UserData", android.content.Context.MODE_PRIVATE)
        val fullName = sharedPref.getString("fullname", "User") ?: "User"
        val emailUser = sharedPref.getString("email", "user@gmail.com") ?: "user@gmail.com"
        val username = sharedPref.getString("username", "user") ?: "user"

        // Set data ke input field
        etFullname.text = Editable.Factory.getInstance().newEditable(fullName)
        etUsername.text = Editable.Factory.getInstance().newEditable(username)
        etEmail.text = Editable.Factory.getInstance().newEditable(emailUser)

        btnSimpan.setOnClickListener {
            val newFullName = etFullname.text.toString()
            val newUsername = etUsername.text.toString()
            val newEmail = etEmail.text.toString()

            // Validasi sederhana
            if (newFullName.isBlank() || newUsername.isBlank() || newEmail.isBlank()) {
                Toast.makeText(requireContext(), "Please complete all the required fields", Toast.LENGTH_SHORT).show()
            } else {
                // Simpan perubahan ke SharedPreferences
                with(sharedPref.edit()) {
                    putString("fullname", newFullName)
                    putString("username", newUsername)
                    putString("email", newEmail)
                    apply()
                }

                Toast.makeText(requireContext(), "Changes have been saved successfully", Toast.LENGTH_SHORT).show()
            }
        }




        return view
    }
}
