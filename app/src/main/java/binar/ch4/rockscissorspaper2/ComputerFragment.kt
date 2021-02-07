package binar.ch4.rockscissorspaper2

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import binar.ch4.rockscissorspaper2.databinding.ActivityHomeBinding
import binar.ch4.rockscissorspaper2.databinding.FragmentComputerBinding
import binar.ch4.rockscissorspaper2.databinding.ResultDialogLayoutBinding
import com.bumptech.glide.Glide

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ComputerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ComputerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentComputerBinding
    private var option1: String = ""
    private var option2: String = ""
    private var state1: Boolean = false
    private var state2: Boolean = false
    private lateinit var selected1: View
    private lateinit var selected2: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentComputerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uploadImage("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
        binding.player1Name.text = param1
        binding.btnReturn.setOnClickListener {
            val fragment = MenuFragment.newInstance(param1.toString(), "")
            activity?.supportFragmentManager!!.beginTransaction().apply {
                replace(ActivityHomeBinding.inflate(layoutInflater).container.id,fragment)
                setReorderingAllowed(true)
                commit()
            }
        }
        binding.btnRefresh.setOnClickListener {
            if(option1 != "" && option2 != ""){
                refresh()
            }
        }

        //Game
        playerOneGame()
    }

    private fun uploadImage(url: String){
        Glide.with(this)
            .load(url)
            .into(binding.gameLogo1)
    }

    private fun playerOneGame() {
        binding.btnRockP1.setOnClickListener {
            playerOneSelect(it)
            computerGame()
            if (option2 != "") {
                checkResult()
            }
        }
        binding.btnPaperP1.setOnClickListener {
            playerOneSelect(it)
            computerGame()
            if (option2 != "") {
                checkResult()
            }
        }
        binding.btnScissorsP1.setOnClickListener {
            playerOneSelect(it)
            computerGame()
            if (option2 != "") {
                checkResult()
            }
        }
    }

    private fun computerGame() {
        lateinit var view: View
        when((1..3).random()){
            1 -> {
                view = binding.btnRockCpu
            }
            2 -> {
                view = binding.btnPaperCpu
            }
            3 -> {
                view = binding.btnScissorsCpu
            }
        }
        computerSelect(view)
        Toast.makeText(activity, "CPU memilih ${view.contentDescription}", Toast.LENGTH_LONG).show()
    }

    private fun playerOneSelect(view: View){
        if (!state1) {
            selected1 = view
            refreshPlayerOne()
            state1 = true
            option1 = selected1.contentDescription.toString()
            view.background = ResourcesCompat.getDrawable(activity!!.resources, R.drawable.bg_selected, null)
        } else {
            refreshPlayerOne()
        }
    }

    private fun computerSelect(view: View) {
        selected2 = view
        option2 = selected2.contentDescription.toString()
        view.background = ResourcesCompat.getDrawable(activity!!.resources, R.drawable.bg_selected, null)
    }

    private fun checkResult(){
        if (option1 == option2) {
            showCustomDialog("Hasil SERI")
        }
        when (option1) {
            "batu" -> {
                when (option2) {
                    "kertas" -> {
                        showCustomDialog("CPU MENANG")
                    }
                    "gunting" -> {
                        showCustomDialog("$param1 MENANG")
                    }
                }
            }
            "kertas" -> {
                when (option2) {
                    "gunting" -> {
                        showCustomDialog("CPU MENANG")
                    }
                    "batu" -> {
                        showCustomDialog("$param1 MENANG")
                    }
                }
            }
            "gunting" -> {
                when (option2) {
                    "batu" -> {
                        showCustomDialog("CPU MENANG")
                    }
                    "kertas" -> {
                        showCustomDialog("$param1 MENANG")
                    }
                }
            }
        }
    }

    private fun refreshPlayerOne() {
        option1 = ""
        state1 = false
        selected1.background = ResourcesCompat.getDrawable(activity!!.resources, R.color.white, null)
    }

    private fun refresh() {
        option1 = ""
        option2 = ""
        state1 = false
        state2 = false
        selected1.background = ResourcesCompat.getDrawable(activity!!.resources, R.color.white, null)
        selected2.background = ResourcesCompat.getDrawable(activity!!.resources, R.color.white, null)
    }

    private fun showCustomDialog(message: String){
        val view = ResultDialogLayoutBinding.inflate(layoutInflater)
        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setView(view.root)
        val dialog = dialogBuilder.create()
        view.dialogMessage.text = message
        view.btnPlayAgain.setOnClickListener {
            refresh()
            dialog.dismiss()
        }
        view.btnBack.setOnClickListener {
            val fragment = MenuFragment.newInstance(param1.toString(), "")
            activity?.supportFragmentManager!!.beginTransaction().apply {
                replace(ActivityHomeBinding.inflate(layoutInflater).container.id,fragment)
                setReorderingAllowed(true)
                commit()
            }
            dialog.dismiss()
        }
        dialog.setCancelable(false)
        dialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ComputerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ComputerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}