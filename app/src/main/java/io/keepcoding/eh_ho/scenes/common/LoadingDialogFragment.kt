package io.keepcoding.eh_ho.scenes.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import io.keepcoding.eh_ho.R
import kotlinx.android.synthetic.main.dialog_loading.*

const val ARG_MESSAGE = "message"

class LoadingDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val message = arguments?.getString(ARG_MESSAGE) ?: getString(R.string.label_loading)
        labelMessage.text = message
    }

    companion object {
        fun newInstance(message: String): LoadingDialogFragment {
            val fragment = LoadingDialogFragment()

            val args = Bundle()
            args.putString(ARG_MESSAGE, message)

            fragment.arguments = args

            return fragment
        }
    }
}