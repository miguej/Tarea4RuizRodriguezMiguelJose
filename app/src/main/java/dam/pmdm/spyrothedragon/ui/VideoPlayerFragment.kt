package dam.pmdm.spyrothedragon.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dam.pmdm.spyrothedragon.R
import dam.pmdm.spyrothedragon.databinding.FragmentVideoPlayerBinding

class VideoPlayerFragment : Fragment() {

    private var _binding: FragmentVideoPlayerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoPath = "android.resource://" + requireContext().packageName + "/" + R.raw.spyrovideo
        val uri = Uri.parse(videoPath)
        binding.videoView.setVideoURI(uri)
        
        binding.videoView.setOnCompletionListener {
            findNavController().popBackStack()
        }

        binding.videoView.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}