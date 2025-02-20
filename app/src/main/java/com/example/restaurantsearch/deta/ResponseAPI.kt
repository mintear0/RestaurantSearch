package com.example.restaurantsearch.deta

import java.io.Serializable

data class ResponseAPI (
    val results: Result
)

data class Result(
    val shop: List<Shop>
)

data class Shop(
    // 固有のID
    val id: String?,                // 店舗ID

    // 距離の絞り込みで使用
    val lat: String?,               // 緯度
    val lng: String?,               // 経度

    // 一覧画面で使用
    val name: String?,              // 店舗名
    val mobile_access: String?,     // 携帯用交通アクセス
    val photo: Photo?,              // 写真
    val budget: Budget?,            // 予算

    // 詳細画面で使用
    val address: String?,           // 住所
    val open: String?,              // 営業時間
    val card: String?               // カード可
): Serializable

data class Photo(
    val pc: Pc?                     // PC向けの写真
): Serializable

data class Pc(
    val l: String?,                 // 店舗トップ写真(大）
    val m: String?                  // 店舗トップ写真(中）
): Serializable

data class Budget(
    val name: String?,              // 検索用予算
    val average: String?            // 平均予算
): Serializable