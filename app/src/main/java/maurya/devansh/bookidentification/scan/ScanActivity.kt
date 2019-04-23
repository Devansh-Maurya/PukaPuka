package maurya.devansh.bookidentification.scan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_scan.*
import maurya.devansh.bookidentification.R

class ScanActivity : AppCompatActivity() {

    private lateinit var lifecycleObserverCamera: LifecycleObserverCamera

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
        lifecycleObserverCamera = LifecycleObserverCamera(this.lifecycle, camera)

        captureButton.setOnClickListener {
            camera.captureImage { cameraKitView, bytes ->

            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        camera.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
