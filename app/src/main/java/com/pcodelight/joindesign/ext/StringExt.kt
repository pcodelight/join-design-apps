package com.pcodelight.joindesign.ext

import android.os.Build
import android.text.Html
import android.text.Spanned

fun String?.fromHtml(): Spanned {
    val text = this ?: ""
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(text)
    }
}

fun String?.replaceDaceIfEmpty(): String {
    return if (!this.isNullOrBlank()) this else "-"
}