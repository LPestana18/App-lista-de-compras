package com.example.listadecompras

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.decodeByteArray


val produtoGlobal = mutableListOf<Produto>()

fun Bitmap.toByteArray(): ByteArray {

    val stream = ByteArrayOutputStream()

    //comprimindo a imagem
    this.compress(android.graphics.Bitmap.CompressFormat.PNG, 0, stream)

    return stream.toByteArray()

}

fun ByteArray.toBitmap() : Bitmap{

    return BitmapFactory.decodeByteArray(this, 0, this.size)
}