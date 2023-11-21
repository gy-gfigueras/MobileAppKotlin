package org.gfigueras.practicaobligatorio.ui.slideshow

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.gfigueras.practicaobligariauniversos.R
import org.gfigueras.practicaobligariauniversos.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    companion object{
        val IMAGE_REQUEST_CODE = 100
    }

    private var _binding: FragmentSlideshowBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}