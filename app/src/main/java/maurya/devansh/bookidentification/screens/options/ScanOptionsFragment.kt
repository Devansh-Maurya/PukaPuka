package maurya.devansh.bookidentification.screens.options


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_scan_options.view.*
import maurya.devansh.bookidentification.MainActivity
import maurya.devansh.bookidentification.R

class ScanOptionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scan_options, container, false)

        view.apply {
            optionBookCoverScan.setOnClickListener {
                view?.findNavController()?.navigate(R.id.bookScanFragment)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.show()
    }
}