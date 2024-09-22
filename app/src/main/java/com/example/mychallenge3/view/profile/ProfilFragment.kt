package com.example.mychallenge3.view.profile

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.work.WorkInfo
import com.example.mychallenge3.R
import com.example.mychallenge3.databinding.FragmentProfilBinding
import com.example.mychallenge3.worker.KEY_IMAGE_URI
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfilFragment : Fragment() {

    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!
    private var currentImageUri: Uri? = null

    private val viewModel: BlurViewModel by viewModel()


    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_LONG).show()
        }
    }

    private fun allPermissionGranted() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            REQUIRED_PERMISSION,
        ) == PackageManager.PERMISSION_GRANTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!allPermissionGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        binding.galleryButton.setOnClickListener {
            openGallery()
        }

        binding.cameraButton.setOnClickListener {
            openCamera()
        }

        binding.btBlur.setOnClickListener {
            viewModel.applyBlur(blurLevel)
        }

        binding.btCancel.setOnClickListener {
            viewModel.cancelWork()
        }

        viewModel.outputWorkInfos.observe(requireActivity(), workInfosObserver())

    }

    private fun workInfosObserver(): Observer<List<WorkInfo>> {
        return Observer { listOfWorkInfo ->

            // Note that these next few lines grab a single WorkInfo if it exists
            // This code could be in a Transformation in the ViewModel; they are included here
            // so that the entire process of displaying a WorkInfo is in one location.

            // If there are no matching work info, do nothing
            if (listOfWorkInfo.isEmpty()) {
                return@Observer
            }

            // We only care about the one output status.
            // Every continuation has only one worker tagged TAG_OUTPUT
            val workInfo = listOfWorkInfo[0]

            if (workInfo.state.isFinished) {
                showWorkFinished()

                // Normally this processing, which is not directly related to drawing views on
                // screen would be in the ViewModel. For simplicity we are keeping it here.
                val outputImageUri = workInfo.outputData.getString(KEY_IMAGE_URI)

                // If there is an output file show "See File" button
                if (!outputImageUri.isNullOrEmpty()) {
                    viewModel.setOutputUri(outputImageUri)
                    binding.previewImageView.setImageURI(outputImageUri.toUri())
                }
            } else {
                showWorkInProgress()
            }
        }
    }

    private fun showWorkInProgress() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            btCancel.visibility = View.VISIBLE
            btBlur.visibility = View.GONE
        }
    }

    /**
     * Shows and hides views for when the Activity is done processing an image
     */
    private fun showWorkFinished() {
        with(binding) {
            progressBar.visibility = View.GONE
            btCancel.visibility = View.GONE
            btBlur.visibility = View.VISIBLE
        }
    }

    private val blurLevel: Int
        get() =
            when (binding.radioBlurGroup.checkedRadioButtonId) {
                R.id.radio_blur_lv_1 -> 1
                R.id.radio_blur_lv_2 -> 2
                R.id.radio_blur_lv_3 -> 3
                else -> 1
            }

    private fun openCamera(){
        currentImageUri = getImageUri(requireContext())
        launcherIntentCamera.launch(currentImageUri!!)


    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
            viewModel.setImageUri(currentImageUri!!)
        }
    }

    private fun openGallery() {
        launcherGallery.launch(PickVisualMediaRequest((ActivityResultContracts.PickVisualMedia.ImageOnly)))
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.previewImageView.setImageURI(it)
        }
    }


    //permission to access gallery
    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
            viewModel.setImageUri(uri)
        } else {
            Log.d("Photo Picker", "No image selected")
        }
    }

    companion object {
        private const val REQUIRED_PERMISSION = android.Manifest.permission.CAMERA
    }
}