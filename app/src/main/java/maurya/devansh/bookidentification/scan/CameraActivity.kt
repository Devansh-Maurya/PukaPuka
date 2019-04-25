package maurya.devansh.bookidentification.scan

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_camera.*
import android.util.Log
import android.widget.Toast
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import maurya.devansh.bookidentification.R


class CameraActivity : AppCompatActivity() {

    private lateinit var lifecycleObservervableCamera: LifecycleObservervableCamera

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        lifecycleObservervableCamera = LifecycleObservervableCamera(this.lifecycle, cameraView)

        captureButton.setOnClickListener {
            cameraView.captureImage { cameraKitView, bytes ->
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

                previewImage.setImageBitmap(bitmap)

                val image = FirebaseVisionImage.fromBitmap(bitmap)

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
                Toast.makeText(this, it.text, Toast.LENGTH_SHORT).show()
                Log.v("Text", it.text)
            }
    }
}
