package maurya.devansh.bookidentification.scan


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_scan_options.view.*

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


}
