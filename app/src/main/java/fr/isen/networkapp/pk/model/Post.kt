package fr.isen.networkapp.pk.model
import android.util.Log
import java.io.Serializable

class Post : Serializable {
    var postTitle: String? = null
    var content: String? = null
    var posterName: String? = null
    var url: String? = null
    var likeBy: MutableList<String>? = null

    var postId: String? = null

    constructor() {

    }

    constructor(title: String, description: String, name: String, imageUrl: String) {
        this.postTitle = title
        this.content = description
        this.posterName = name
        this.url = imageUrl
        this.likeBy = mutableListOf<String>()
        this.likeBy!!.add("initForDb")
    }
}