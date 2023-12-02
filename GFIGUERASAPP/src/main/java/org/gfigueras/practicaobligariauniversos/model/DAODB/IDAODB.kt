package org.gfigueras.practicaobligariauniversos.model.DAODB

import org.gfigueras.practicaobligariauniversos.model.entities.Mapa
import org.gfigueras.practicaobligariauniversos.model.entities.Universo
import org.gfigueras.practicaobligariauniversos.model.*

/**
 * Interfaz IDAODB (DB Universos & Mapas)
 * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
 */
interface IDAODB{
    /**
     * Lista universos
     * @return Retorna una lista de Universos
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun listUniversos(): MutableList<Universo>

    /**
     * getUniverso, Recibe un universo por codigo y lo busca, puede retornar null
     * @return retona un Objeto Universo || null
     * @param codigo de universo, del 0 al 10
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getUniverso(cod:Int): Universo?

    /**
     * getUniverso, Recibe un universo por nombre y lo busca, puede retornar null
     * @return retorna Objeto Universo || null
     * @param name Nombre del universo:String
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getUniverso(name:String): Universo?

    /**
     * listarMapas, lista todos los mapas
     * @return Retorna una lista de mapas
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun listMapas(): MutableList<Mapa>
    /**
     * getUniverso, Recibe un Mapa por codigo y lo busca, puede retornar null
     * @return retona un Objeto Mapa || null
     * @param codigo de Mapa
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getMapa(cod:Int): Mapa?
    /**
     * getUniverso, Recibe un Mapa por nombre y lo busca, puede retornar null
     * @return retorna Objeto Mapa || null
     * @param name Nombre del Mapa:String
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getMapa(name:String): Mapa?

    /**
     * Retorna todos los mapas de un universo
     * @return Lista de Mapas || null
     * @param mundo Nombre del Universo
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getMapas(mundo:String):MutableList<Mapa>?
    /**
     * Retorna todos los mapas de un universo
     * @return Lista de Mapas || null
     * @param mundo Codigo del Universo
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getMapas(mundo: Int): MutableList<Mapa>?





}