package binar.ch4.rockscissorspaper2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import binar.ch4.rockscissorspaper2.databinding.ActivityHomeBinding
import binar.ch4.rockscissorspaper2.databinding.FragmentMenuBinding
import com.google.android.material.snackbar.Snackbar

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.menuTextPemain.text = "$param1 vs Pemain"
        binding.menuTextKomputer.text = "$param1 vs Komputer"

        val snackbar = Snackbar.make(binding.root, "Selamat Datang $param1", Snackbar.LENGTH_LONG)
        snackbar.setAction("Tutup"){
            snackbar.dismiss()
        }
        snackbar.show()

        binding.menuLogoPemain.setOnClickListener {
            val fragment = Player2Fragment.newInstance(param1.toString(), "")
            activity?.supportFragmentManager!!.beginTransaction().apply {
                replace(ActivityHomeBinding.inflate(layoutInflater).container.id,fragment)
                setReorderingAllowed(true)
                addToBackStack(null)
                commit()
            }
        }

        binding.menuLogoKomputer.setOnClickListener {
            val fragment = ComputerFragment.newInstance(param1.toString(), "")
            activity?.supportFragmentManager!!.beginTransaction().apply {
                replace(ActivityHomeBinding.inflate(layoutInflater).container.id,fragment)
                setReorderingAllowed(true)
                addToBackStack(null)
                commit()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}