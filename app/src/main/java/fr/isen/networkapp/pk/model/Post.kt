package fr.isen.networkapp.pk.model
import java.io.Serializable

class Post : Serializable {
    var postTitle: String? = null
    var content: String? = null
    var posterName: String? = null
    var likeNumber: Int = 0

    constructor() {

    }

    constructor(title: String, description: String, name: String) {
        this.postTitle = title
        this.content = description
        this.posterName = name
        this.likeNumber = 0
    }
}