package maurya.devansh.bookidentification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val navController = findNavController(R.id.navHostFragment)
        if (navController.currentDestination?.id == R.id.booksListFragment) {
            navController.navigate(R.id.action_booksListFragment_to_scanOptionsFragment)
        } else
            super.onBackPressed()
    }
}
