package com.example.kiemtra2

import android.widget.CheckBox
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var tvThongTinCongViec: TextView
    private lateinit var congViec: CongViec

    private val chinhSuaLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { ketQua ->

        if (ketQua.resultCode == RESULT_OK) {
            val duLieu = ketQua.data

            if (duLieu != null) {
                congViec.tenCongViec =
                    duLieu.getStringExtra("tenCongViec")
                        ?: congViec.tenCongViec

                congViec.moTa =
                    duLieu.getStringExtra("moTa")
                        ?: congViec.moTa

                hienThiThongTin()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_cong_viec)

        tvThongTinCongViec =
            findViewById(R.id.tvThongTinCongViec)

        val btnChinhSua =
            findViewById<Button>(R.id.btnChinhSua)

        val btnHoanThanh =
            findViewById<Button>(R.id.btnHoanThanh)

        val chkDaHoanThanh =
            findViewById<CheckBox>(R.id.chkDaHoanThanh)

        congViec = layCongViecTuIntent()

        hienThiThongTin()

        btnHoanThanh.setOnClickListener {
            congViec.trangThai = true

            chkDaHoanThanh.isChecked = true

            hienThiThongTin()
        }

        chkDaHoanThanh.isChecked = congViec.trangThai

        chkDaHoanThanh.setOnCheckedChangeListener {
                _, daHoanThanh ->

            congViec.trangThai = daHoanThanh

            hienThiThongTin()
        }

        btnChinhSua.setOnClickListener {
            val intentChinhSua = Intent(
                this,
                ChinhSuaCongViecActivity::class.java
            )

            intentChinhSua.putExtra(
                "tenCongViec",
                congViec.tenCongViec
            )

            intentChinhSua.putExtra(
                "moTa",
                congViec.moTa
            )

            chinhSuaLauncher.launch(intentChinhSua)
        }
    }


    @Suppress("DEPRECATION")
    private fun layCongViecTuIntent(): CongViec {
        val viTri =
            intent.getIntExtra("viTriCongViec", -1)

        if (viTri in KhoCongViec.danhSach.indices) {
            return KhoCongViec.danhSach[viTri]
        }

        val congViecNhan =
            intent.getSerializableExtra("congViec") as? CongViec

        return congViecNhan ?: CongViec(
            maCongViec = "CV-KHONG-RO",
            tenCongViec =
                intent.getStringExtra("tenCongViec") ?: "",
            moTa =
                intent.getStringExtra("moTa") ?: ""
        )
    }

    private fun hienThiThongTin() {
        tvThongTinCongViec.text = congViec.layThongTin()
    }
}