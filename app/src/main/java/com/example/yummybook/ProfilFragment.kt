package com.example.yummybook

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView

class ProfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profil, container, false)

        // menampilakan nama lengkap user
        val sharedPreff = requireActivity().getSharedPreferences("UserData", android.content.Context.MODE_PRIVATE)
        val fullName = sharedPreff.getString("fullname", "User") // "User" sebagai default

        val textView = view.findViewById<TextView>(R.id.tvNama)
        textView.text = "$fullName"

        // menampilakan username
        val sharedPref = requireActivity().getSharedPreferences("UserData", android.content.Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "User") // "User" sebagai default

        val textVieww = view.findViewById<TextView>(R.id.tvUsername)
        textVieww.text = "Username: $username"

        // menampilakan email
        val sharedPrefff = requireActivity().getSharedPreferences("UserData", android.content.Context.MODE_PRIVATE)
        val email = sharedPrefff.getString("email", "User@gmail.com") // "User" sebagai default

        val textViewww = view.findViewById<TextView>(R.id.tvUserEmail)
        textViewww.text = "Email: $email"

        val editProfil = view.findViewById<Button>(R.id.btnEditProfile)

        editProfil.setOnClickListener {
            val detailFragment = EditProfilFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit()
        }

        // keluar dari info login saat ini, jadi akan pergi halaman awal
        val buttonKeluar = view.findViewById<Button>(R.id.btnLogout)

        buttonKeluar.setOnClickListener {
//            val intent = Intent(requireActivity(), HalamanAwal::class.java)
//            startActivity(intent)

            AlertDialog.Builder(requireContext())
                .setTitle("Log out of Account?")
                .setMessage("You will be logged out of the current login session. Are you sure you want to continue?")
                .setPositiveButton("Yes") { _, _ ->

                    // Navigasi ke LoginActivity (atau halaman lain)
                    val intent = Intent(requireContext(), HalamanAwal::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Cancel", null)
                .show()

        }

        // ke menu hapus akun
        val buttonHapusAkun = view.findViewById<Button>(R.id.btnHapusAkun)

        buttonHapusAkun.setOnClickListener {
            // Ganti fragment manual
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, HapusAkunFragment())
            fragmentTransaction.addToBackStack(null) // supaya bisa klik tombol back
            fragmentTransaction.commit()
        }






        return view
    }

}