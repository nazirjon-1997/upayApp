package com.developer.upayonline.models

class GroupModel {
    var id:Int = 0
    var name: String? = null
    var category: String? = null

    constructor(id: Int, name: String?, category: String?) {
        this.id = id
        this.name = name
        this.category = category
    }
}