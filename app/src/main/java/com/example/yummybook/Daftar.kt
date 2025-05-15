package com.example.yummybook

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class Daftar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_daftar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Untuk pindah halaman ke syarat dan ketentuan
        val textView = findViewById<CheckBox>(R.id.cekBox)
        val fullText = "By creating an account, you agree to our Terms & Conditions."
        val spannableString = SpannableString(fullText)


        // Tentukan bagian teks yang ingin dibuat link
        val clickableText = "Terms & Conditions"
        val startIndex = fullText.indexOf(clickableText)
        val endIndex = startIndex + clickableText.length

        // Buat clickable span
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Aksi saat teks diklik ➔ pindah halaman
                val intent = Intent(this@Daftar, SyaratKetentuan::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false // Tidak pakai garis bawah
                ds.color = Color.parseColor("#5249A1")
            }
        }

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
        textView.highlightColor = Color.TRANSPARENT


        // button udah punya akun -> masuk
        val pindahMasuk = findViewById<TextView>(R.id.tv_pindahMasuk)

        pindahMasuk.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        // button back
        val kembalihalamanawal = findViewById<ImageView>(R.id.iv_kembali)

        kembalihalamanawal.setOnClickListener {
            val intent = Intent(this, HalamanAwal::class.java)
            startActivity(intent)
        }

        // button daftar
        val masukBeranda = findViewById<Button>(R.id.btn_daftar)

        masukBeranda.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // cek form masuk apakah sudah diisi semua
        // Input field
        val namaLengkap = findViewById<TextInputEditText>(R.id.et_fullname)
        val usernameEditText = findViewById<TextInputEditText>(R.id.et_username)
        val passwordEditText = findViewById<TextInputEditText>(R.id.et_password)
        val emailUser = findViewById<TextInputEditText>(R.id.et_email)
        val ceklisSyarat = findViewById<CheckBox>(R.id.cekBox)
        val buttonDaftar = findViewById<Button>(R.id.btn_daftar)

        // Proses daftar
        buttonDaftar.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val nama = namaLengkap.text.toString().trim()
            val email = emailUser.text.toString().trim()
            val isChecked = ceklisSyarat.isChecked

            if (username.isNotEmpty() && password.isNotEmpty() && nama.isNotEmpty() &&
                email.isNotEmpty() && isChecked) {

                // Simpan data email dan password ke SharedPreferences
                val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("username", username)
                editor.putString("password", password)
                editor.putString("fullname", nama)
                editor.putString("email", email)
                editor.apply()

                Toast.makeText(this, "Registration successful! Please login", Toast.LENGTH_SHORT).show()

                // Pindah ke halaman login
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please complete all the required fields", Toast.LENGTH_SHORT).show()
            }
        }


    }
}