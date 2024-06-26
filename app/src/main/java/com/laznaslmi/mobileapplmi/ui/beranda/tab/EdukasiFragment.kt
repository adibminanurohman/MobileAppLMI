package com.laznaslmi.mobileapplmi.ui.beranda.tab

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.laznaslmi.mobileapplmi.databinding.FragmentEdukasiBinding
import com.laznaslmi.mobileapplmi.ui.beranda.DetailEdukasiActivity
import com.laznaslmi.mobileapplmi.ui.beranda.EdukasiViewModel
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.EdukasiAdapter
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.EdukasiDataClass

class EdukasiFragment : Fragment() {

    private var _binding: FragmentEdukasiBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val edukasiViewModel =
            ViewModelProvider(this).get(EdukasiViewModel::class.java)

        _binding = FragmentEdukasiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Recycler view
        val recyclerView = binding.rvEdukasi
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Adapter
        val adapter = EdukasiAdapter(emptyList(), object : EdukasiAdapter.OnItemClickListener {
            override fun onItemClick(edukasi: EdukasiDataClass) {
                val intent = Intent(requireContext(), DetailEdukasiActivity::class.java)
                intent.putExtra("edukasi", edukasi)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter

        // Observe
        edukasiViewModel.edukasiList.observe(viewLifecycleOwner) { edukasiList ->
            edukasiList?.let { adapter.updateData(it) }
            binding.swipeRefreshLayout.isRefreshing = false
        }

        edukasiViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

        // Setup SwipeRefreshLayout
        binding.swipeRefreshLayout.setOnRefreshListener {
            edukasiViewModel.refreshData()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
