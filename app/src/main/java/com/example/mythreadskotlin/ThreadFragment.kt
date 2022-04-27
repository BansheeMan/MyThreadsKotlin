package com.example.mythreadskotlin

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        val myThreads = MyThreads().apply { start() }
        with(binding) {
            val time = editText.text.toString().toLong()
            var counter = 0
            //ПАРАЛЛЕЛЬНОЕ ВЫПОЛНЕНИЕ
            button1.setOnClickListener {
                Thread {
                    sleep(time * 1000L)
                    requireActivity().runOnUiThread {
                        textView1.text = "$time ceк."
                        createTextView("${Thread.currentThread().name} ${++counter}")
                    }
                    //Handler(Looper.getMainLooper()).post { textView.text = "$time ceк." }
                }.start()
            }

            //ВЕЧНЫЙ ПОТОК!!! ПОСЛЕДОВАТЕЛЬНОЕ ВЫПОЛНЕНИЕ
            button2.setOnClickListener {
                myThreads.mHandler?.post {
                    sleep(time*1000L)
                    Handler(Looper.getMainLooper()).post {
                        textView2.text = "$time сек."
                        createTextView("${Thread.currentThread().name} ${++counter}")
                    }
                }
            }
        }
    }

    private fun createTextView(name:String) {
        binding.mainContainer.addView(TextView(requireContext()).apply {
            text = name
            textSize = 14f
        })
    }

    class MyThreads: Thread() {
        var mHandler: Handler? = null
        override fun run() {
            Looper.prepare()
            mHandler = Handler(Looper.myLooper()!!)
            Looper.loop()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ThreadFragment()
    }
}