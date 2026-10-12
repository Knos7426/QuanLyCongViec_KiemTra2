package com.example.kiemtra2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChinhSuaCongViecActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chinh_sua_cong_viec)

        val edtTen =
            findViewById<EditText>(R.id.edtTenCongViecSua)

        val edtMoTa =
            findViewById<EditText>(R.id.edtMoTaCongViecSua)

        val btnLuu =
            findViewById<Button>(R.id.btnLuuCongViec)

        edtTen.setText(intent.getStringExtra("tenCongViec") ?: "")
        edtMoTa.setText(intent.getStringExtra("moTa") ?: "")

        btnLuu.setOnClickListener {
            val tenMoi = edtTen.text.toString().trim()
            val moTaMoi = edtMoTa.text.toString().trim()

            if (tenMoi.isBlank()) {
                edtTen.error = "Ten cong viec khong duoc de trong"
                edtTen.requestFocus()
                return@setOnClickListener
            }

            val ketQua = Intent()
            ketQua.putExtra("tenCongViec", tenMoi)
            ketQua.putExtra("moTa", moTaMoi)

            setResult(RESULT_OK, ketQua)
            finish()
        }
        }
    }