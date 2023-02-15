package com.udacity.shoestore.utils

import androidx.databinding.InverseMethod

object Converter {
    @InverseMethod("doubleToString")
    @JvmStatic
    fun stringToDouble(value: String): Double? {
        return if (value == "") {
            0.0
        } else {
            value.toDouble()
        }
    }

    @JvmStatic
    fun doubleToString(value: Double): String? {
        return if (value == 0.0) {
            ""
        } else if (value / value.toInt() == 1.0) {
            value.toInt().toString()
        } else {
            value.toString()
        }
    }
}