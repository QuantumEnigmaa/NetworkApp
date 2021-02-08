package fr.isen.networkapp.pk.model

class Image {
    private var imageURL: String? = null

    constructor() {

    }

    constructor(url: String) {
        this.imageURL = url
    }

    fun getImageUrl(): String? {
        return this.imageURL
    }
}