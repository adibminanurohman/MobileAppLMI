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
import com.laznaslmi.mobileapplmi.databinding.FragmentMitraBinding
import com.laznaslmi.mobileapplmi.databinding.FragmentProgramBinding
import com.laznaslmi.mobileapplmi.ui.beranda.DetailMitraActivity
import com.laznaslmi.mobileapplmi.ui.beranda.DetailProgramActivity
import com.laznaslmi.mobileapplmi.ui.beranda.MitraViewModel
import com.laznaslmi.mobileapplmi.ui.beranda.ProgramViewModel
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.MitraAdapter
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.MitraDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.ProgramAdapter
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.ProgramDataClass

class MitraFragment : Fragment() {

    private var _binding: FragmentMitraBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mitraViewModel =
            ViewModelProvider(this).get(MitraViewModel::class.java)

        _binding = FragmentMitraBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //recycler view
        val recyclerView = binding.rvMitra
        recyclerView.layoutManager = LinearLayoutManager(context)

        //adapter
        val adapter = MitraAdapter(emptyList(), object: MitraAdapter.OnItemClickListener{
            override fun onItemClick(mitra: MitraDataClass) {
                val intent = Intent(requireContext(), DetailMitraActivity::class.java)
                intent.putExtra("mitra", mitra)
                startActivity(intent)
            }

        })
        recyclerView.adapter = adapter

        //observe
        mitraViewModel.mitraList.observe(viewLifecycleOwner){mitraList->
            mitraList?.let { adapter.updateData(it) }
        }

        mitraViewModel.errorMessage.observe(viewLifecycleOwner){errorMessage->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
