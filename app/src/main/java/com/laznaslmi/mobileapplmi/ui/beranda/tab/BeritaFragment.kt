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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.laznaslmi.mobileapplmi.databinding.FragmentBeritaBinding
import com.laznaslmi.mobileapplmi.ui.beranda.BannersViewModel
import com.laznaslmi.mobileapplmi.ui.beranda.BeritaViewModel
import com.laznaslmi.mobileapplmi.ui.beranda.DetailBannersActivity
import com.laznaslmi.mobileapplmi.ui.beranda.DetailBeritaActivity
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.BannersAdapter
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.BeritaAdapter
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.BeritaDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.PostDataClass

class BeritaFragment : Fragment() {

    private var _binding: FragmentBeritaBinding? = null
    private val binding get() = _binding!!

    private lateinit var bannersViewModel: BannersViewModel
    private lateinit var beritaViewModel: BeritaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeritaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize ViewModels
        bannersViewModel = ViewModelProvider(this).get(BannersViewModel::class.java)
        beritaViewModel = ViewModelProvider(this).get(BeritaViewModel::class.java)

        // Setup SwipeRefreshLayout
        val swipeRefreshLayout: SwipeRefreshLayout = binding.swipeRefresh
        swipeRefreshLayout.setOnRefreshListener {
            // Refresh action here
            fetchData()
        }

        // RecyclerView configuration
        val recyclerViewBanners = binding.rvBanners
        recyclerViewBanners.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // RecyclerView configuration
        val recyclerViewBerita = binding.rvBerita
        recyclerViewBerita.layoutManager = LinearLayoutManager(context)

        // Adapter for Banners
        val adapterBanners = BannersAdapter(emptyList(), object: BannersAdapter.OnItemClickListener {
            override fun onItemClick(banners: PostDataClass) {
                val intent = Intent(requireContext(), DetailBannersActivity::class.java)
                intent.putExtra("banners", banners)
                startActivity(intent)
            }
        })

        // Adapter for Berita
        val adapterBerita = BeritaAdapter(emptyList(), object: BeritaAdapter.OnItemClickListener {
            override fun onItemClick(berita: BeritaDataClass) {
                val intent = Intent(requireContext(), DetailBeritaActivity::class.java)
                intent.putExtra("berita", berita)
                startActivity(intent)
            }
        })

        recyclerViewBanners.adapter = adapterBanners
        recyclerViewBerita.adapter = adapterBerita

        // Observe BannersViewModel
        bannersViewModel.bannersList.observe(viewLifecycleOwner) { bannersList ->
            bannersList?.let { adapterBanners.updateData(it) }
            swipeRefreshLayout.isRefreshing = false // Stop refresh animation
        }
        bannersViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                swipeRefreshLayout.isRefreshing = false // Stop refresh animation
            }
        }

        // Observe BeritaViewModel
        beritaViewModel.beritaList.observe(viewLifecycleOwner) { beritaList ->
            beritaList?.let { adapterBerita.updateData(it) }
            swipeRefreshLayout.isRefreshing = false // Stop refresh animation
        }
        beritaViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                swipeRefreshLayout.isRefreshing = false // Stop refresh animation
            }
        }

        // Initial data load
        fetchData()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchData() {
        bannersViewModel.fetchData()
        beritaViewModel.fetchData()
    }
}
