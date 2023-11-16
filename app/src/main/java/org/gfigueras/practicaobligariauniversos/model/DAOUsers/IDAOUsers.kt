package org.gfigueras.practicaobligariauniversos.model.DAOUsers

interface IDAOUsers {

    suspend fun login(username:String, password:String):Boolean
    fun signUp(username:String, email:String, password:String):Boolean
    fun closeClient()
}