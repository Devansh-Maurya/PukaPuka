package maurya.devansh.bookidentification.screens.coverscan

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText
import kotlinx.android.synthetic.main.fragment_cover_scan.*
import maurya.devansh.bookidentification.R
import maurya.devansh.bookidentification.util.LifecycleObservableCamera


class CoverScanFragment : Fragment() {

    private lateinit var lifecycleObservableCamera: LifecycleObservableCamera


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_cover_scan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleObservableCamera =
            LifecycleObservableCamera(this.lifecycle, cameraView)

        captureButton.setOnClickListener {
            cameraView.captureImage { cameraKitView, bytes ->
                val bitmapImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                val image = FirebaseVisionImage.fromBitmap(bitmapImage)
                scanImage(image)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        cameraView.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun scanImage(image: FirebaseVisionImage) {
        val detector = FirebaseVision.getInstance().onDeviceTextRecognizer

        detector.processImage(image)
            .addOnSuccessListener {
                val usefulText = extractUsefulTextFromResult(it)
                Toast.makeText(context, usefulText, Toast.LENGTH_SHORT).show()
                Log.v("Text", usefulText)
                //Using SafeArgs to pass arguments between fragments
                val action = CoverScanFragmentDirections.actionCoverScanDone(usefulText)
                findNavController().navigate(action)
            }
    }

    private fun extractUsefulTextFromResult(result: FirebaseVisionText): String {
        val stringBuilder = StringBuilder()

        for (block in result.textBlocks) {
            for (line in block.lines) {
                val lineText = line.text

                Log.v("lineText", lineText)

                if (containsMeaningfulText(lineText))
                    stringBuilder.append("$lineText ")
            }
        }

        return stringBuilder.toString()
    }

    //More ignore cases can be added later
    private fun containsMeaningfulText(string: String) =
                !(string.contains("for sale", true)
                        || string.contains("fer sale", true)
                        || string.contains("far sale", true)
                        || string.contains("nepal, bangla", true)
                        || string.contains("bhutan only", true)
                        || string.contains("edition", true)
                        //Ignore all publishers names
                        || string.contains("TATA McGRAW-HILL", true)
                        || string.contains("McGraw-Hill", true)
                        || string.contains("HarperCollins", true)
                        || string.contains("Cengage", true)
                        || string.contains("Wiley", true)
                        || string.contains("Springer", true)
                        || string.contains("scholastic", true)
                        || string.contains("pearson", true)
                        || string.contains("oxford", true)
                        || string.contains("university press", true)
                        || string.contains("cambridge", true)
                        || string.contains("phi", true)
                        )
}
