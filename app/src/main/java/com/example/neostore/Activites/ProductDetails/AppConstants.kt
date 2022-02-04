package com.example.neostore.Activites.ProductDetails

import android.os.Environment
import java.io.File

object AppConstants {
    val MEDIA_FOLDER = Environment.getExternalStorageDirectory()
        .toString() + File.separator + "NeoStore_Downloaded_Images"
    val DOWNLOADS_FOLDER = MEDIA_FOLDER + File.separator + "Downloads/"
}

