package com.yigitmidye.app.admin.util

fun currentScreen(index : Int) : String {
    return when(index) {
        0 -> "Onay bekleyen siparişler"
        1 -> "Onaylanan siparişler"
        2 -> "Yola çıkan şiparişler"
        3 -> "Teslim edilen siparişler"
        4 -> "İptal edilen siparişler"
        else -> "Bilinmeyen durum"
    }
}