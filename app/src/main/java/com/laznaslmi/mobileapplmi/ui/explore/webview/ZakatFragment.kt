package com.laznaslmi.mobileapplmi.ui.explore.webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.webkit.WebViewClient
import com.laznaslmi.mobileapplmi.databinding.FragmentZakatBinding

class ZakatFragment : Fragment() {

    private var _binding: FragmentZakatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentZakatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dapatkan referensi WebView dari layout
        val webView = binding.wvZakat

        // Aktifkan javascript
        webView.settings.javaScriptEnabled = true

        // Set WebViewClient untuk menghindari membuka browser eksternal
        webView.webViewClient = WebViewClient()

        // Muat URL infak.in
        webView.loadUrl("http://infak.or.id/")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}