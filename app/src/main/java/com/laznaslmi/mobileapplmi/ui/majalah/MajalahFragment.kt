package com.laznaslmi.mobileapplmi.ui.majalah

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.laznaslmi.mobileapplmi.databinding.FragmentMajalahBinding

class MajalahFragment : Fragment() {

private var _binding: FragmentMajalahBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val notificationsViewModel =
            ViewModelProvider(this).get(MajalahViewModel::class.java)

    _binding = FragmentMajalahBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textMajalah
    notificationsViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}