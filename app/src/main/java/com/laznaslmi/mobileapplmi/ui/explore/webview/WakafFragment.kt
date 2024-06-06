package com.laznaslmi.mobileapplmi.ui.explore.webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.webkit.WebViewClient
import com.laznaslmi.mobileapplmi.databinding.FragmentWakafBinding

class WakafFragment : Fragment() {

    private var _binding: FragmentWakafBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWakafBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dapatkan referensi WebView dari layout
        val webView = binding.wvWakaf

        // Aktifkan javascript
        webView.settings.javaScriptEnabled = true

        // Set WebViewClient untuk menghindari membuka browser eksternal
        webView.webViewClient = WebViewClient()

        // Muat URL infak.in
        webView.loadUrl("https://wakafo.org/")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}