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
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bannersViewModel =
            ViewModelProvider(this).get(BannersViewModel::class.java)

        val beritaViewModel =
            ViewModelProvider(this).get(BeritaViewModel::class.java)

        _binding = FragmentBeritaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // RecyclerView configuration
        val recyclerView = binding.rvBanners
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        //recycler view
        val recyclerViewBerita = binding.rvBerita
        recyclerViewBerita.layoutManager = LinearLayoutManager(context)
        // Adapter
        val adapter = BannersAdapter(emptyList(), object: BannersAdapter.OnItemClickListener {
            override fun onItemClick(banners: PostDataClass) {
                val intent = Intent(requireContext(), DetailBannersActivity::class.java)
                intent.putExtra("banners", banners)
                startActivity(intent)
            }
        })

        // Adapter
        val adapterBerita = BeritaAdapter(emptyList(), object: BeritaAdapter.OnItemClickListener {
            override fun onItemClick(berita: BeritaDataClass) {
                val intent = Intent(requireContext(), DetailBeritaActivity::class.java)
                intent.putExtra("berita", berita)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter
        recyclerViewBerita.adapter = adapterBerita

        // Observe
        bannersViewModel.bannersList.observe(viewLifecycleOwner) { bannersList ->
            bannersList?.let { adapter.updateData(it) }
        }
        bannersViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }

        // Observe
        beritaViewModel.beritaList.observe(viewLifecycleOwner) { beritaList ->
            beritaList?.let { adapterBerita.updateData(it) }
        }
        beritaViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
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
