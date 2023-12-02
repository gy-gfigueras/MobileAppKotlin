package org.gfigueras.practicaobligariauniversos.ui.universos

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
/**
 * Clase que proporciona espaciado uniforme entre los elementos de un RecyclerView con diseño de cuadrícula para los universos
 * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
 */
class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {
    /**
     * Calcula y establece los desplazamientos (márgenes) para los elementos del RecyclerView(Universos)
     * @param outRect Rectángulo de salida que contiene los márgenes a aplicar.
     * @param view Vista actual del elemento del RecyclerView.
     * @param parent RecyclerView al que pertenece el elemento.
     * @param state Estado actual del RecyclerView.
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing
            }
            outRect.bottom = spacing // item bottom
        } else {
            outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing // item top
            }
        }
    }
}
