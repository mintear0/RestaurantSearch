package com.example.restaurantsearch


class HotPepperGourmetSearch {
    private var lat: Double? = null // 緯度
    private var lng: Double? = null // 経度
    private var lunch = 0 // ランチ営業有無
    private var range = 0 // 検索範囲距離
    private var midnight_meal = 0 // // 23時以降食事OK
    private var keywordList: ArrayList<String>? = null // キーワードのリスト
    private var genreCdList: ArrayList<String>? = null // ジャンルのリスト

    fun getLat(): Double? {
        return lat
    }

    fun setLat(lat: Double?) {
        this.lat = lat
    }

    fun getLng(): Double? {
        return lng
    }

    fun setLng(lng: Double?) {
        this.lng = lng
    }

    fun getLunch(): Int {
        return lunch
    }

    fun setLunch(lunch: Int) {
        this.lunch = lunch
    }

    fun getRange(): Int {
        return range
    }

    fun setRange(range: Int) {
        this.range = range
    }

    fun getMidnight_meal(): Int {
        return midnight_meal
    }

    fun setMidnight_meal(midnight_meal: Int) {
        this.midnight_meal = midnight_meal
    }

    fun getGenreCdList(): ArrayList<String>? {
        return genreCdList
    }

    fun setGenreCdList(genreCdList: ArrayList<String>?) {
        this.genreCdList = genreCdList
    }

    fun getKeywordList(): ArrayList<String>? {
        return keywordList
    }

    fun setKeywordList(keywordList: ArrayList<String>?) {
        this.keywordList = keywordList
    }
}