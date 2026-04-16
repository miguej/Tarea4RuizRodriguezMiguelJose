package dam.pmdm.spyrothedragon.ui.guide

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import dam.pmdm.spyrothedragon.R
import dam.pmdm.spyrothedragon.databinding.FragmentGuidePage5Binding

import androidx.navigation.fragment.findNavController

class GuidePage5Fragment : DialogFragment() {

    private var _binding: FragmentGuidePage5Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuidePage5Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Animacion de latido
        val pulseAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.pulse)
        binding.llSpeechBubble.startAnimation(pulseAnimation)

        binding.btnNext.setOnClickListener {
            playSound(R.raw.gemaspyro)
            findNavController().navigate(R.id.action_guidePage5_to_guidePage6)
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