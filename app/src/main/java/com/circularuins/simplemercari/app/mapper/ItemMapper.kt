package com.circularuins.simplemercari.app.mapper

import com.circularuins.simplemercari.domain.model.Item
import java.text.NumberFormat
import java.util.Locale

fun Item.convert(): com.circularuins.simplemercari.app.viewdata.Item {
    return com.circularuins.simplemercari.app.viewdata.Item(
        id,
        name,
        isSoldOut(),
        numLikes,
        numComments,
        numToCurrency(price),
        photo
    )
}

fun Item.isSoldOut(): Boolean =
    when (status) {
        Item.Status.SoldOut -> true
        Item.Status.OnSale, Item.Status.None -> false
    }

fun numToCurrency(num: Int): String {
    val format = NumberFormat.getCurrencyInstance(Locale.US)
    format.maximumFractionDigits = 1
    return format.format(num)
}