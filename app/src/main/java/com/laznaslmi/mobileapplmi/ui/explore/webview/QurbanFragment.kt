package com.laznaslmi.mobileapplmi.ui.explore.webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.webkit.WebViewClient
import com.laznaslmi.mobileapplmi.databinding.FragmentQurbanBinding

class QurbanFragment : Fragment() {

    private var _binding: FragmentQurbanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQurbanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dapatkan referensi WebView dari layout
        val webView = binding.wvQurban

        // Aktifkan javascript
        webView.settings.javaScriptEnabled = true

        // Set WebViewClient untuk menghindari membuka browser eksternal
        webView.webViewClient = WebViewClient()

        // Muat URL infak.in
        webView.loadUrl("https://qurbanholic.lmizakat.id/")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}