package org.gfigueras.practicaobligariauniversos.model.entities

class Universo(private var codigo: Int, private var nombre: String, private var descripcion: String, private var imagen: String){
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

    override fun toString(): String {
        return String.format("Universo[cod: %d, Nombre: %s, Descripcion: %s]",this.codigo, this.nombre, this.descripcion)
    }

}