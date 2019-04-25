package maurya.devansh.bookidentification.scan

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.camerakit.CameraKitView

/**
 * Created by Devansh on 23/4/19.
 */

class LifecycleObservervableCamera(lifecycle: Lifecycle, val cameraKitView: CameraKitView) : LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        cameraKitView.onStart()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        cameraKitView.onResume()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        cameraKitView.onPause()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        cameraKitView.onStop()
    }
}