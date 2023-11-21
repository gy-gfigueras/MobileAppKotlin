package org.gfigueras.practicaobligariauniversos.controller

import org.gfigueras.practicaobligariauniversos.model.entities.Mapa
import org.gfigueras.practicaobligariauniversos.model.entities.Universo

interface IController {
    fun listUniversos(): MutableList<Universo>
    fun getUniverso(cod:Int): Universo?
    fun getUniverso(name:String): Universo?
    fun listMapas(): MutableList<Mapa>
    fun getMapa(cod:Int): Mapa?
    fun getMapa(name:String): Mapa?
    fun getMapas(mundo:String):MutableList<Mapa>?
    fun getMapas(mundo: Int): MutableList<Mapa>?
    suspend fun login(username:String, password:String):Boolean
    suspend fun signUp(username:String, email:String, password:String):Int
    suspend fun getUser(username: String, password: String): String


}