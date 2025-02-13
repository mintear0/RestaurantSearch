package com.example.restaurantsearch

class HotPepperGourmetReturn {

    private var name: String? = null // 飲食店の名前
    private var address: String? = null // 住所
    private var lat: Double? = null // お店の緯度
    private var lng: Double? = null // お店の経度
    private var lunch: String? = null // ランチ有無
    private var url: String? = null // お店のURL
    private var id: String? = null // お店コード

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getAddress(): String? {
        return address
    }

    fun setAddress(address: String?) {
        this.address = address
    }

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

    fun getLunch(): String? {
        return lunch
    }

    fun setLunch(lunch: String?) {
        this.lunch = lunch
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String?) {
        this.url = url
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }
}