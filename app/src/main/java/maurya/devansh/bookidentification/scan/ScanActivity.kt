package maurya.devansh.bookidentification.scan

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_scan.*
import android.graphics.Bitmap
import com.camerakit.CameraKitView.CameraListener
import maurya.devansh.bookidentification.R
import java.io.ByteArrayOutputStream


class ScanActivity : AppCompatActivity() {

    private lateinit var lifecycleObserverCamera: LifecycleObserverCamera

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
        lifecycleObserverCamera = LifecycleObserverCamera(this.lifecycle, cameraView)

        captureButton.setOnClickListener {
            cameraView.captureImage { cameraKitView, bytes ->
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        cameraView.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
