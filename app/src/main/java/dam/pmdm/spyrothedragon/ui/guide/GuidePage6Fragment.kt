package dam.pmdm.spyrothedragon.ui.guide

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import dam.pmdm.spyrothedragon.R
import dam.pmdm.spyrothedragon.databinding.FragmentGuidePage6Binding

class GuidePage6Fragment : DialogFragment() {

    private var _binding: FragmentGuidePage6Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuidePage6Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Animación de ampliación (Zoom In)
        val zoomInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.zoom_in)
        binding.root.startAnimation(zoomInAnimation)

        binding.btnReady.setOnClickListener {
            // Se termina la guia
            playSound(R.raw.spyroladron)
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