package org.allanh.newpipeextractorsample.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import org.allanh.newpipeextractorsample.databinding.FragmentMainBinding
import org.allanh.newpipeextractorsample.viewmodels.MainViewModel
import org.allanh.newpipeextractorsample.viewmodels.Result

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val url = binding.editText.text.toString()
            viewModel.getYoutubeUrl(url)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.youtubeUrlState.collect { result ->
                when (result) {
                    is Result.Success -> {
                        // TODO: use exoplayer to play the video url
                        Log.d(TAG, "url: ${result.data}")
                    }
                    is Result.Error -> {
                        // TODO: error handling
                        result.exception.message?.let { Log.e(TAG, "Error: $it") }
                    }
                    is Result.Loading -> {
                        // TODO: display loading animation
                        Log.d(TAG, "Loading...")
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainFragment"
    }
}