package dam.pmdm.spyrothedragon.ui.guide

import android.animation.ObjectAnimator
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import dam.pmdm.spyrothedragon.R
import dam.pmdm.spyrothedragon.databinding.FragmentGuidePage1Binding

class GuidePage1Fragment : DialogFragment() {

    private var _binding: FragmentGuidePage1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuidePage1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Usamos objectanimator
        ObjectAnimator.ofFloat(binding.ivSpyroLogo, "alpha", 0f, 1f).apply {
            duration = 1000
            start()
        }
        ObjectAnimator.ofFloat(binding.tvTitle, "alpha", 0f, 1f).apply {
            duration = 1000
            start()
        }
        ObjectAnimator.ofFloat(binding.tvDescription, "alpha", 0f, 1f).apply {
            duration = 1000
            start()
        }

        binding.btnStart.setOnClickListener {
            playSound(R.raw.gemaspyro)
            findNavController().navigate(R.id.action_guidePage1_to_guidePage2)
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