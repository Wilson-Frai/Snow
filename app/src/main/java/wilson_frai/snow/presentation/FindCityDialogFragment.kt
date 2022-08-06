package wilson_frai.snow.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import wilson_frai.snow.databinding.DialogFragmentFindCityBinding

class FindCityDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogFragmentFindCityBinding.inflate(layoutInflater)

        binding.dialogFindCityCancelButton.setOnClickListener {
            dismiss()
        }

        binding.dialogFindCityFindButton.setOnClickListener {
            val city = binding.dialogFindCityCityEditText.text.toString()
            if (city != "" && city.length > 2) {
                parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(KEY_RESPONSE to city))
                dismiss()
            } else {
                Toast.makeText(this.context, "Please, enter city name.", Toast.LENGTH_SHORT).show()
            }
        }

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()
    }

    companion object {
        @JvmStatic
        val TAG = FindCityDialogFragment::class.java.simpleName
        @JvmStatic
        val REQUEST_KEY = "$TAG:defaultRequestKey"
        @JvmStatic
        val KEY_RESPONSE = "RESPONSE"
    }
}