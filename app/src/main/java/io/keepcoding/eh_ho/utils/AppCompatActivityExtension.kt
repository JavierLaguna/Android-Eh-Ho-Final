package io.keepcoding.eh_ho.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.isFirstTimeCreated(savedInstanceState: Bundle?): Boolean =
    savedInstanceState == null