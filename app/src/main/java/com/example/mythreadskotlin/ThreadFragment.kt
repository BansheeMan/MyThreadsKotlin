package com.example.mythreadskotlin

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mythreadskotlin.databinding.FragmentThreadBinding
import java.lang.Thread.sleep

class ThreadFragment : Fragment() {

    private var _binding: FragmentThreadBinding? = null
    private val binding: FragmentThreadBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThreadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            button.setOnClickListener {
                Thread {
                    val time = editText.text.toString().toLong()
                    sleep(time * 1000L)
                    requireActivity().runOnUiThread { textView.text = "$time ceк." }
                    //Handler(Looper.getMainLooper()).post { textView.text = "$time ceк." }
                }.start()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ThreadFragment()
    }
}