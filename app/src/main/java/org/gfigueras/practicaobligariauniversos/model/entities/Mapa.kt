package org.gfigueras.practicaobligariauniversos.model.entities

class Mapa(private var codigo: Int, private var nombre: String, private var descripcion: String, private var imagen: String, private var universo: Universo){
    fun getNombre():String{
        return this.nombre
    }
    fun getCodigo():Int{
        return this.codigo
    }
    fun getDescripcion():String{
        return this.descripcion
    }
    fun getImagen():String{
        return this.imagen
    }
    fun getUniverso(): Universo {
        return this.universo
    }

    override fun toString(): String {
        return String.format("Universo[cod: %d, Nombre: %s, Descripcion: %s, Universo: %s]",this.codigo, this.nombre, this.descripcion, this.universo.getNombre())
    }
}