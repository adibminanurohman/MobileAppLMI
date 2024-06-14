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
import com.laznaslmi.mobileapplmi.databinding.FragmentProgramBinding
import com.laznaslmi.mobileapplmi.ui.beranda.DetailProgramActivity
import com.laznaslmi.mobileapplmi.ui.beranda.ProgramViewModel
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.ProgramAdapter
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.ProgramDataClass

class ProgramFragment : Fragment() {

    private var _binding: FragmentProgramBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val programViewModel =
            ViewModelProvider(this).get(ProgramViewModel::class.java)

        _binding = FragmentProgramBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Recycler view
        val recyclerView = binding.rvProgram
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Adapter
        val adapter = ProgramAdapter(emptyList(), object : ProgramAdapter.OnItemClickListener {
            override fun onItemClick(program: ProgramDataClass) {
                val intent = Intent(requireContext(), DetailProgramActivity::class.java)
                intent.putExtra("program", program)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter

        // Observe
        programViewModel.programList.observe(viewLifecycleOwner) { programList ->
            programList?.let { adapter.updateData(it) }
            binding.swipeRefreshLayout.isRefreshing = false
        }

        programViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

        // Setup SwipeRefreshLayout
        binding.swipeRefreshLayout.setOnRefreshListener {
            programViewModel.refreshData()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
