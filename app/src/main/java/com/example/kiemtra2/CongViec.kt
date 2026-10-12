package com.example.kiemtra2

import java.io.Serializable

data class CongViec(
    var maCongViec: String,
    var tenCongViec: String,
    var moTa: String,
    var trangThai: Boolean = false
) : Serializable {

    fun laDaHoanThanh(): Boolean {
        return trangThai
    }

    fun layThongTin(): String {
        val trangThaiHienThi =
            if (laDaHoanThanh()) {
                "Da hoan thanh"
            } else {
                "Chua hoan thanh"
            }

        return "Ma cong viec: $maCongViec\n\n" +
                "Ten cong viec: $tenCongViec\n\n" +
                "Mo ta: $moTa\n\n" +
                "Trang thai: $trangThaiHienThi"
    }
}

object KhoCongViec {
    val danhSach = mutableListOf<CongViec>()
}