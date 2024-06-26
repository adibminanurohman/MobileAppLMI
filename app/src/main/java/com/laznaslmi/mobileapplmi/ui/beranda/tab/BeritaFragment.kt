package com.laznaslmi.mobileapplmi.ui.beranda.tab

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.laznaslmi.mobileapplmi.R
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
import java.text.SimpleDateFormat
import java.util.*

class BeritaFragment : Fragment() {

    private var _binding: FragmentBeritaBinding? = null
    private val binding get() = _binding!!

    private lateinit var beritaViewModel: BeritaViewModel

    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var dotIndicator: LinearLayout

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

        // Attach SnapHelper to RecyclerView
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewBanners)

        // Dot indicator setup
        dotIndicator = binding.dotIndicator

        // RecyclerView configuration for berita
        val recyclerViewBerita = binding.rvBerita
        recyclerViewBerita.layoutManager = LinearLayoutManager(context)
        val adapterBerita = BeritaAdapter(emptyList(), object : BeritaAdapter.OnItemClickListener {
            override fun onItemClick(berita: BeritaDataClass) {
                val intent = Intent(requireContext(), DetailBeritaActivity::class.java)
                intent.putExtra("berita", berita)
                startActivity(intent)
            }
        })
        recyclerViewBerita.adapter = adapterBerita

        // Add scroll listener to RecyclerView
        recyclerViewBanners.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val activePosition = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (activePosition != RecyclerView.NO_POSITION) {
                    updateActiveDot(activePosition)
                }
            }
        })

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
                        // Sort banners by created_at date in descending order
                        val sortedBanners = banners.sortedByDescending { parseDate(it.created_at) }
                        val limitedBanners = sortedBanners.take(5)
                        bannerAdapter.updateData(limitedBanners)
                        setupDots(limitedBanners.size)
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

    private fun parseDate(dateString: String): Date? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())
        return dateFormat.parse(dateString)
    }

    private fun setupDots(size: Int) {
        dotIndicator.removeAllViews()
        for (i in 0 until size) {
            val dot = ImageView(context).apply {
                setImageResource(R.drawable.dot_inactive) // Assuming you have this drawable
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    marginStart = 8
                    marginEnd = 8
                }
                layoutParams = params
            }
            dotIndicator.addView(dot)
        }
        updateActiveDot(0)
    }

    private fun updateActiveDot(activePosition: Int) {
        for (i in 0 until dotIndicator.childCount) {
            val dot = dotIndicator.getChildAt(i) as ImageView
            if (i == activePosition) {
                dot.setImageResource(R.drawable.dot_active) // Assuming you have this drawable
            } else {
                dot.setImageResource(R.drawable.dot_inactive) // Assuming you have this drawable
            }
        }
    }
}
