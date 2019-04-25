package maurya.devansh.bookidentification.scan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import maurya.devansh.bookidentification.R

class ScanOnboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scanLinearLayout.setOnClickListener {
//            val image = FirebaseVisionImage.fromBitmap(BitmapFactory.decodeResource(
//                    resources, R.drawable.sample
//            ))
//
//            val detector = FirebaseVision.getInstance().onDeviceTextRecognizer
//            val result = detector.processImage(image)
//                    .addOnSuccessListener {
//                        Toast.makeText(this, it.text, Toast.LENGTH_SHORT).show()
//                        Log.v("Text", it.text)
//                    }
            findNavController(R.id.nav_graph).navigate(R.id.cameraActivity)
        }

    }
}
