package com.example.kiemtra2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtTenCongViec =
            findViewById<EditText>(R.id.edtTenCongViec)

        val edtMoTaCongViec =
            findViewById<EditText>(R.id.edtMoTaCongViec)

        val btnTaoCongViec =
            findViewById<Button>(R.id.btnTaoCongViec)

        val btnXemDanhSach =
            findViewById<Button>(R.id.btnXemDanhSach)

        btnTaoCongViec.setOnClickListener {
            val ten = edtTenCongViec.text.toString().trim()
            if (ten.isBlank()) {
                edtTenCongViec.error =
                    "Ten cong viec khong duoc de trong"

                edtTenCongViec.requestFocus()
                return@setOnClickListener
            }

            val moTa = edtMoTaCongViec.text.toString().trim()

            val maCongViec = "CV${System.currentTimeMillis()}"

            val congViec = CongViec(
                maCongViec = maCongViec,
                tenCongViec = ten,
                moTa = moTa
            )

            KhoCongViec.danhSach.add(congViec)

            val viTriCongViec =
                KhoCongViec.danhSach.lastIndex

            val intentChiTiet = Intent(
                this,
                ChiTietCongViecActivity::class.java
            )

            intentChiTiet.putExtra("congViec", congViec)
            intentChiTiet.putExtra(
                "viTriCongViec",
                viTriCongViec
            )

            startActivity(intentChiTiet)
        }

        btnXemDanhSach.setOnClickListener {
            if (KhoCongViec.danhSach.isEmpty()) {
                AlertDialog.Builder(this)
                    .setTitle("Danh sach cong viec")
                    .setMessage("Chua co cong viec nao.")
                    .setPositiveButton("Dong", null)
                    .show()
            } else {
                val noiDung =
                    KhoCongViec.danhSach.mapIndexed {
                            index, congViec ->

                        val trangThai =
                            if (congViec.trangThai) {
                                "Da hoan thanh"
                            } else {
                                "Chua hoan thanh"
                            }

                        "${index + 1}. ${congViec.tenCongViec}\n" +
                                "Trang thai: $trangThai"
                    }.joinToString("\n\n")

                AlertDialog.Builder(this)
                    .setTitle("Danh sach cong viec")
                    .setMessage(noiDung)
                    .setPositiveButton("Dong", null)
                    .show()
            }
        }
    }
}