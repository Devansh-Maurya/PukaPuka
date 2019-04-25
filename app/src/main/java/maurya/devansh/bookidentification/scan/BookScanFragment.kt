package maurya.devansh.bookidentification.scan

import android.graphics.BitmapFactory
import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_book_scan.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText
import maurya.devansh.bookidentification.R
import java.lang.StringBuilder


class BookScanFragment : Fragment() {

    private lateinit var lifecycleObservableCamera: LifecycleObservableCamera


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_book_scan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleObservableCamera = LifecycleObservableCamera(this.lifecycle, cameraView)

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

        val result = detector.processImage(image)
            .addOnSuccessListener {
                Toast.makeText(context, extractMeaningfulTextFromResult(it), Toast.LENGTH_SHORT).show()
                Log.v("Text", extractMeaningfulTextFromResult(it))
            }


    }

    private fun extractMeaningfulTextFromResult(result: FirebaseVisionText): String {
        val stringBuilder = StringBuilder()

        for (block in result.textBlocks) {
            for (line in block.lines) {
                val lineText = line.text

                Log.v("lineText", lineText)

                if (containsMeaningfulText(lineText))
                    stringBuilder.append(lineText + '\n')
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
