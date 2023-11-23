package org.gfigueras.practicaobligariauniversos.model.entities

class User(private var username: String, private var email: String, private var role:String, private var favoriteUniverso:Universo?) {
    fun getUsername():String{
        return this.username
    }
    fun getEmail():String{
        return this.email
    }
    fun getRole():String{
        return this.role
    }
    fun setUsername(username:String){
        this.username = username
    }
    fun setEmail(email: String){
        this.email = email
    }

    fun setFavoriteUniverso(universo: Universo?){
        this.favoriteUniverso = universo
    }

    fun getFavoriteUniverso():Universo?{
        return this.favoriteUniverso
    }

    override fun toString():String{
        return "Usuario: ${this.username}, Email: ${this.email}, Role: ${this.role}, UniversoFav: $favoriteUniverso"
    }

}