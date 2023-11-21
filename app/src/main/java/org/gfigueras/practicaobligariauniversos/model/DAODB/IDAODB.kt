package org.gfigueras.practicaobligariauniversos.model.DAODB

import org.gfigueras.practicaobligariauniversos.model.entities.Mapa
import org.gfigueras.practicaobligariauniversos.model.entities.Universo
import org.gfigueras.practicaobligariauniversos.model.*

interface IDAODB{
    fun listUniversos(): MutableList<Universo>
    fun getUniverso(cod:Int): Universo?
    fun getUniverso(name:String): Universo?

    fun listMapas(): MutableList<Mapa>
    fun getMapa(cod:Int): Mapa?
    fun getMapa(name:String): Mapa?
    fun getMapas(mundo:String):MutableList<Mapa>?
    fun getMapas(mundo: Int): MutableList<Mapa>?





}