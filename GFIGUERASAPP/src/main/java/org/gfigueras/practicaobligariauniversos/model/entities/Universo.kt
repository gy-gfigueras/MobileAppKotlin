package org.gfigueras.practicaobligariauniversos.model.entities
/**
 * Clase Universo
 * @param codigo
 * @param nombre
 * @param descripcion
 * @param imagen
 * @return Retorna un Objeto Universo
 * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
 */
class Universo(private var codigo: Int, private var nombre: String, private var descripcion: String, private var imagen: String){
    /**
     * Getter de el atributo Nombre
     * @return Retorna un String(Nombre de this object)
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getNombre():String{
        return this.nombre
    }
    /**
     * Getter de el atributo Codigo
     * @return Retorna un Int(Codigo de this object)
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getCodigo():Int{
        return this.codigo
    }
    /**
     * Getter de el atributo Descripcion
     * @return Retorna un String(Descripcion de this object)
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getDescripcion():String{
        return this.descripcion
    }
    /**
     * Getter de el atributo Imagen
     * @return Retorna una url en forma de  String
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getImagen():String{
        return this.imagen
    }
    /**
     * ToString
     * @return Retorna un String Universo[cod: 0, Nombre; aaa, Descripcion: aaa]
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    override fun toString(): String {
        return String.format("Universo[cod: %d, Nombre: %s, Descripcion: %s]",this.codigo, this.nombre, this.descripcion)
    }

}