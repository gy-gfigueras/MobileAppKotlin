package org.gfigueras.practicaobligariauniversos.model.entities
/**
 * Clase User
 * @param username
 * @param email
 * @param role
 * @param favoriteUniverso obtiene un objeto universo, por codigo y lo asocia al objeto
 * @return Retorna un Objeto User
 * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
 */
class User(private var username: String, private var email: String, private var role:String, private var favoriteUniverso:Universo?) {
    /**
     * Getter de el atributo Username
     * @return Retorna un String(Username de this object)
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getUsername():String{
        return this.username
    }
    /**
     * Getter de el atributo email
     * @return Retorna un String(Email de this object)
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getEmail():String{
        return this.email
    }
    /**
     * Getter de el atributo Role
     * @return Retorna un String(Role de this object) == ADMIN || USER
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getRole():String{
        return this.role
    }
    /**
     * Setter de el atributo username
     * @param username
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun setUsername(username:String){
        this.username = username
    }

    /**
     * Setter de el atributo email
     * @param email
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun setEmail(email: String){
        this.email = email
    }
    /**
     * Setter de el atributo FavoriteUniverso
     * @param universo
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun setFavoriteUniverso(universo: Universo?){
        this.favoriteUniverso = universo
    }
    /**
     * Getter de el atributo FavoriteUniverso
     * @return Retorna un Universo(Universo de this object)
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getFavoriteUniverso():Universo?{
        return this.favoriteUniverso
    }
    /**
     * ToString
     * @return Retorna un String = Usuario: username, Email: email, Role: ROLE, UniversoFav: Object[]
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    override fun toString():String{
        return "Usuario: ${this.username}, Email: ${this.email}, Role: ${this.role}, UniversoFav: $favoriteUniverso"
    }

}