package dam.pmdm.spyrothedragon.ui.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import dam.pmdm.spyrothedragon.R
import dam.pmdm.spyrothedragon.databinding.FragmentGuidePage2Binding

class GuidePage2Fragment : DialogFragment() {

    private var _binding: FragmentGuidePage2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuidePage2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aplicamos la animación de rebote al bocadillo
        val bounceAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.bounce)
        binding.llSpeechBubble.startAnimation(bounceAnimation)

        binding.btnNext.setOnClickListener {
            // Aquí navegaremos a la siguiente pantalla de la guía (Paso 3)
            // findNavController().navigate(R.id.action_guidePage2_to_guidePage3)
            dismiss() // Por ahora cerramos para ver el efecto
        }
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