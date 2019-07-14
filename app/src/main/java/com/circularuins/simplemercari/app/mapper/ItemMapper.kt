package com.circularuins.simplemercari.app.mapper

import com.circularuins.simplemercari.domain.model.Item
import java.text.NumberFormat
import java.util.Locale

/**
 * Item(Model) -> Item(ViewData)への変換ロジック
 */
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

/**
 * ステータスを元に販売中かどうかのフラグに変換する
 *
 * @return 販売状態を示すフラグ
 */
fun Item.isSoldOut(): Boolean =
    when (status) {
        Item.Status.SoldOut -> true
        Item.Status.OnSale, Item.Status.None -> false
    }

/**
 * 値段の数値をUSドル表記文字列に変換する
 *
 * @param num 値段
 * @return 例："$99"
 */
fun numToCurrency(num: Int): String {
    val format = NumberFormat.getCurrencyInstance(Locale.US)
    format.maximumFractionDigits = 1
    return format.format(num)
}