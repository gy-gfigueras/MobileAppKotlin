package org.gfigueras.practicaobligariauniversos.model.entities

/**
 * Clase Mapa
 * @param codigo
 * @param nombre
 * @param descripcion
 * @param imagen
 * @param universo vinculado en la base de dato por relacion 1:N
 * @return Retorna un Objeto Mapa
 * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
 */
class Mapa(private var codigo: Int, private var nombre: String, private var descripcion: String, private var imagen: String, private var universo: Universo){
    /**
     * Getter de el atributo nombre
     * @return Retorna un String(nombre de this object)
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getNombre():String{
        return this.nombre
    }
    /**
     * Getter de el atributo codigo
     * @return Retorna un Int(codigo de this object)
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
     * @return Retorna un String con la url (Imagen de this object)
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getImagen():String{
        return this.imagen
    }
    /**
     * Getter de el atributo Universo
     * @return Retorna un Objeto Universo
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getUniverso(): Universo {
        return this.universo
    }
    /**
     * ToString
     * @return Retorna un String Mapa[cod: 0, Nombre; aaa, Descripcion: aaa, Universo: Object[]]
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    override fun toString(): String {
        return String.format("Mapa[cod: %d, Nombre: %s, Descripcion: %s, Universo: %s]",this.codigo, this.nombre, this.descripcion, this.universo.getNombre())
    }
}