package fr.isen.networkapp.pk.model

import android.text.Editable


class User {
    var username: String? = null
    var uid:String?=null
    constructor(name: Editable, id:String ) {
        username= name.toString()
        uid=id
    }

}