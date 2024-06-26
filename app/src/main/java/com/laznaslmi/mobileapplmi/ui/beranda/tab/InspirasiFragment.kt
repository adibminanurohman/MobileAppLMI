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
import com.laznaslmi.mobileapplmi.databinding.FragmentInspirasiBinding
import com.laznaslmi.mobileapplmi.ui.beranda.DetailInspirasiActivity
import com.laznaslmi.mobileapplmi.ui.beranda.InspirasiViewModel
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.InspirasiAdapter
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.InspirasiDataClass

class InspirasiFragment : Fragment() {

    private var _binding: FragmentInspirasiBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inspirasiViewModel =
            ViewModelProvider(this).get(InspirasiViewModel::class.java)

        _binding = FragmentInspirasiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Recycler view
        val recyclerView = binding.rvInspirasi
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Adapter
        val adapter = InspirasiAdapter(emptyList(), object : InspirasiAdapter.OnItemClickListener {
            override fun onItemClick(inspirasi: InspirasiDataClass) {
                val intent = Intent(requireContext(), DetailInspirasiActivity::class.java)
                intent.putExtra("inspirasi", inspirasi)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter

        // Observe
        inspirasiViewModel.inspirasiList.observe(viewLifecycleOwner) { inspirasiList ->
            inspirasiList?.let { adapter.updateData(it) }
            binding.swipeRefreshLayout.isRefreshing = false
        }

        inspirasiViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

        // Setup SwipeRefreshLayout
        binding.swipeRefreshLayout.setOnRefreshListener {
            inspirasiViewModel.refreshData()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
