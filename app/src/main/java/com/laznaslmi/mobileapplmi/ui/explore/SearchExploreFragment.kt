package com.laznaslmi.mobileapplmi.ui.explore

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.laznaslmi.mobileapplmi.R
import com.laznaslmi.mobileapplmi.databinding.FragmentSearchExploreBinding
import com.laznaslmi.mobileapplmi.ui.explore.retrofit.SearchExploreAdapter
import com.laznaslmi.mobileapplmi.ui.explore.retrofit.SearchExploreDataClass

class SearchExploreFragment : Fragment() {

    private var _binding: FragmentSearchExploreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val searchExploreViewModel =
            ViewModelProvider(this).get(SearchExploreViewModel::class.java)

        _binding = FragmentSearchExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // recycler view
        val recyclerView = binding.recyclerViewSearchExplore
        recyclerView.layoutManager = LinearLayoutManager(context)

        // adapter
        val adapter = SearchExploreAdapter(emptyList(), object: SearchExploreAdapter.OnItemClickListener{
            override fun onItemClick(searchExplore: SearchExploreDataClass) {
                val intent = Intent(requireContext(), DetailSearchExploreActivity::class.java)
                intent.putExtra("searchExplore", searchExplore)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter

        // observe
        searchExploreViewModel.searchExploreList.observe(viewLifecycleOwner) { searchExploreList ->
            searchExploreList?.let { adapter.updateData(it) }
        }

        searchExploreViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }

        // search
        val searchView: androidx.appcompat.widget.SearchView = binding.searchSearchExplore
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchExploreViewModel.searchExplore(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchExploreViewModel.searchExplore(newText)
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
