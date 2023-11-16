package org.gfigueras.practicaobligariauniversos.model.DAOUsers

interface IDAOUsers {

    suspend fun login(username:String, password:String):Boolean
    suspend fun signUp(username:String, email:String, password:String):Int
    fun closeClient()
}