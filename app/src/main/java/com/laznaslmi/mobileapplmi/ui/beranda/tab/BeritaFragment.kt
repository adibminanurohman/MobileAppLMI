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
import com.laznaslmi.mobileapplmi.ui.beranda.BeritaViewModel
import com.laznaslmi.mobileapplmi.ui.beranda.DetailBeritaActivity
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.BannerAdapter
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.BannerResponse
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.BeritaAdapter
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.BeritaDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeritaFragment : Fragment() {

    private var _binding: FragmentBeritaBinding? = null
    private val binding get() = _binding!!

    private lateinit var beritaViewModel: BeritaViewModel

    private lateinit var bannerAdapter: BannerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeritaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize ViewModels
        beritaViewModel = ViewModelProvider(this).get(BeritaViewModel::class.java)

        // Setup SwipeRefreshLayout
        val swipeRefreshLayout: SwipeRefreshLayout = binding.swipeRefresh
        swipeRefreshLayout.setOnRefreshListener {
            // Refresh action here
            fetchData()
        }

        // RecyclerView configuration for banners
        val recyclerViewBanners = binding.rvBanners
        recyclerViewBanners.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        bannerAdapter = BannerAdapter(emptyList(), requireContext())
        recyclerViewBanners.adapter = bannerAdapter

        // RecyclerView configuration for berita
        val recyclerViewBerita = binding.rvBerita
        recyclerViewBerita.layoutManager = LinearLayoutManager(context)
        val adapterBerita = BeritaAdapter(emptyList(), object: BeritaAdapter.OnItemClickListener {
            override fun onItemClick(berita: BeritaDataClass) {
                val intent = Intent(requireContext(), DetailBeritaActivity::class.java)
                intent.putExtra("berita", berita)
                startActivity(intent)
            }
        })
        recyclerViewBerita.adapter = adapterBerita


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
        beritaViewModel.fetchData()

        // Fetch Banners
        val call = RetrofitClient.instance.getBanners()
        call.enqueue(object : Callback<BannerResponse> {
            override fun onResponse(call: Call<BannerResponse>, response: Response<BannerResponse>) {
                if (response.isSuccessful) {
                    val banners = response.body()?.banners
                    if (!banners.isNullOrEmpty()) {
                        bannerAdapter.updateData(banners)
                    }
                } else {
                    Toast.makeText(requireContext(), "Failed to fetch banners", Toast.LENGTH_SHORT).show()
                }
                binding.swipeRefresh.isRefreshing = false
            }

            override fun onFailure(call: Call<BannerResponse>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                binding.swipeRefresh.isRefreshing = false
            }
        })
    }
}
