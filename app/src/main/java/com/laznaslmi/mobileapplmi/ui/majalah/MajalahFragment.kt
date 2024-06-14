package com.laznaslmi.mobileapplmi.ui.majalah

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.laznaslmi.mobileapplmi.R
import com.laznaslmi.mobileapplmi.databinding.FragmentMajalahBinding

class MajalahFragment : Fragment() {

    private var _binding: FragmentMajalahBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val majalahViewModel =
            ViewModelProvider(this)[MajalahViewModel::class.java]

        _binding = FragmentMajalahBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // RecyclerView
        val recyclerView = binding.recyclerViewMajalah
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Adapter
        val adapter = MajalahAdapter(emptyList(), object: MajalahAdapter.OnItemClickListener {
            override fun onItemClick(majalah: MajalahDataClass) {
                val intent = Intent(requireContext(), DetailMajalahActivity::class.java)
                intent.putExtra("majalah", majalah)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter

        // Observe data
        majalahViewModel.majalahList.observe(viewLifecycleOwner) { majalahList ->
            majalahList?.let {
                adapter.updateData(it)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

        majalahViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

        // SwipeRefreshLayout
        val swipeRefreshLayout: SwipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            majalahViewModel.fetchData()
        }

        // Search functionality
        val searchView: androidx.appcompat.widget.SearchView = binding.searchMajalah
        searchView.queryHint = "Cari Majalah"

        searchView.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    majalahViewModel.searchMajalah(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    majalahViewModel.searchMajalah(newText)
                }
                return true
            }
        })

        // Mengubah background EditText di dalam SearchView untuk menghilangkan garis bawah
        val searchEditText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchEditText.setHintTextColor(Color.parseColor("#808080"))

        // Menghilangkan background default dan mengatur background custom
        searchEditText.background = null
        searchEditText.setBackgroundResource(R.drawable.custom_edittext_background)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
