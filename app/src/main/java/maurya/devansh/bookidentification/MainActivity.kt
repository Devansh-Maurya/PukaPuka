package maurya.devansh.bookidentification

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scanButton.setOnClickListener {
            val image = FirebaseVisionImage.fromBitmap(BitmapFactory.decodeResource(
                    resources, R.drawable.sample))

            val detector = FirebaseVision.getInstance().onDeviceTextRecognizer
            val result = detector.processImage(image)
                    .addOnSuccessListener {
                        Toast.makeText(this, it.text, Toast.LENGTH_SHORT).show()
                        Log.v("Text", it.text)
                    }
        }

    }
}
