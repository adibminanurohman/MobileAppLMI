package com.laznaslmi.mobileapplmi.ui.explore

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.laznaslmi.mobileapplmi.R
import com.laznaslmi.mobileapplmi.databinding.FragmentExploreBinding

class ExploreFragment : Fragment() {

private var _binding: FragmentExploreBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val dashboardViewModel =
            ViewModelProvider(this).get(SearchExploreViewModel::class.java)

    _binding = FragmentExploreBinding.inflate(inflater, container, false)
    val root: View = binding.root

      //Explore to searchExplore
      val cvSearch: CardView = binding.cvSearchExplore
      cvSearch.setOnClickListener {
          findNavController().navigate(R.id.action_navigation_explore_to_searchExploreFragment)
      }

      //Explore to infaq
      val cvInfaq: CardView = binding.cvInfaq
      cvInfaq.setOnClickListener {
          findNavController().navigate(R.id.action_navigation_explore_to_infaqFragment)
      }

      //Explore to wakaf
      val cvWakaf: CardView = binding.cvWakaf
      cvWakaf.setOnClickListener {
          findNavController().navigate(R.id.action_navigation_explore_to_wakafFragment)
      }

      //Explore to zakat
      val cvZakat: CardView = binding.cvZakat
      cvZakat.setOnClickListener {
          findNavController().navigate(R.id.action_navigation_explore_to_zakatFragment)
      }

      //Explore to qurban
      val cvQurban: CardView = binding.cvQurban
      cvQurban.setOnClickListener {
          findNavController().navigate(R.id.action_navigation_explore_to_qurbanFragment)
      }

      val cvKonsultasi: CardView = binding.cvKonsultasi
      cvKonsultasi.setOnClickListener {
          val phoneNumber = "6282230000909" // Ganti dengan nomor WhatsApp yang ingin dihubungi
          val url = "https://wa.me/$phoneNumber"
          val intent = Intent(Intent.ACTION_VIEW)
          intent.data = Uri.parse(url)
          context?.startActivity(intent)
      }

    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}