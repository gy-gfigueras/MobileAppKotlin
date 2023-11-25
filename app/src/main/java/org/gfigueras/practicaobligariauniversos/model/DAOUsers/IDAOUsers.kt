package org.gfigueras.practicaobligariauniversos.model.DAOUsers

import org.gfigueras.practicaobligariauniversos.model.entities.Universo

interface IDAOUsers {

    suspend fun login(username:String, password:String):Boolean
    suspend fun signUp(username:String, email:String, password:String):Int
    suspend fun getUser(username: String, password: String): String
    suspend fun changePassword(username:String, password: String, passwordNew: String):Int
    suspend fun setUniverseFav(username: String,universo: Universo?):Boolean
    suspend fun deleteUser(username:String, password: String, usernameToDelete:String):Boolean
    suspend fun getUsers():String?

        fun closeClient()
}