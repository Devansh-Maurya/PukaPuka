package maurya.devansh.bookidentification.scan

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

    private lateinit var lifecycleObserverCamera: LifecycleObserverCamera

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        lifecycleObserverCamera = LifecycleObserverCamera(this.lifecycle, cameraView)

        captureButton.setOnClickListener {
            cameraView.captureImage { cameraKitView, bytes ->
//                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

                val metadata = FirebaseVisionImageMetadata.Builder()
                    .setWidth(334) // 480x360 is typically sufficient for
                    .setHeight(500) // image recognition
                    .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
//                    .setRotation(RotationCalculator.getRotationCompensation(
//                        this@CameraActivity, this@CameraActivity))
                    .build()

                val image = FirebaseVisionImage.fromByteArray(bytes, metadata)

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
