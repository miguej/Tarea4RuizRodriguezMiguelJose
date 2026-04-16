package dam.pmdm.spyrothedragon.ui.guide

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dam.pmdm.spyrothedragon.R
import dam.pmdm.spyrothedragon.databinding.FragmentGuidePage3Binding

class GuidePage3Fragment : DialogFragment() {

    private var _binding: FragmentGuidePage3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuidePage3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aplicamos la animación de rebote al bocadillo
        val bounceAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.bounce)
        binding.llSpeechBubble.startAnimation(bounceAnimation)

        binding.btnNext.setOnClickListener {
            playSound(R.raw.gemaspyro)
            // Cambiamos la pestaña a Coleccionables
            val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.navView)
            bottomNav?.selectedItemId = R.id.nav_collectibles
            
            // Nos vamos al diálogo de la Pantalla 4
            findNavController().navigate(R.id.guidePage4Fragment)
        }
        // Cerramos la guia
        binding.btnSkip.setOnClickListener {
            markGuideAsFinished()
            dismiss()
        }
    }

    private fun playSound(resId: Int) {
        val mediaPlayer = MediaPlayer.create(requireContext(), resId)
        mediaPlayer.setOnCompletionListener { it.release() }
        mediaPlayer.start()
    }
    // Marcamos la guia si la saltamos
    private fun markGuideAsFinished() {
        val sharedPreferences = requireActivity().getSharedPreferences("SpyroPrefs", android.content.Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("guide_finished", true).apply()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}