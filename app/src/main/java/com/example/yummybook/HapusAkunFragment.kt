package com.example.yummybook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText

class HapusAkunFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hapus_akun, container, false)

        val etPassword = view.findViewById<TextInputEditText>(R.id.et_password)
        val btnHapus = view.findViewById<Button>(R.id.btn_hapus_akun)

        val sharedPref = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val savedPassword = sharedPref.getString("password", "")

        btnHapus.setOnClickListener {
            val inputPassword = etPassword.text.toString()

            if (inputPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter your password first", Toast.LENGTH_SHORT).show()
            } else if (inputPassword != savedPassword) {
                Toast.makeText(requireContext(), "Incorrect password", Toast.LENGTH_SHORT).show()
            } else {
                // Kata sandi benar, konfirmasi penghapusan
                AlertDialog.Builder(requireContext())
                    .setTitle("Confirm Account Deletion")
                    .setMessage("Are you sure you want to permanently delete this account?")
                    .setPositiveButton("Yes") { _, _ ->
                        sharedPref.edit().clear().apply()

                        // Navigasi ke LoginActivity (atau halaman lain)
                        val intent = Intent(requireContext(), HalamanAwal::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }
                    .setNegativeButton("No", null)
                    .show()
            }
        }

        // tombol kembali ke fragment pengaturan
        val ivKembali = view.findViewById<ImageView>(R.id.iv_kembali)

        ivKembali.setOnClickListener {
            parentFragmentManager.popBackStack()

            // Tampilkan lagi BottomNavigationView
            val bottomNav =
                requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNav.visibility = View.VISIBLE

        }

        // Sembunyikan BottomNavigationView
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.visibility = View.GONE

        return view
    }
}
