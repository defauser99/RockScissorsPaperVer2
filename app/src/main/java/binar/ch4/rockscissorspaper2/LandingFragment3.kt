package binar.ch4.rockscissorspaper2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import binar.ch4.rockscissorspaper2.databinding.FragmentLanding3Binding

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class LandingFragment3 : Fragment() {
    private  lateinit var binding: FragmentLanding3Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLanding3Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToHome.setOnClickListener {
            val name = binding.etName.text?.toString()?.trim()
            if (name.isNullOrEmpty()) {
                Toast.makeText(activity, "Nama tidak boleh kosong!",Toast.LENGTH_SHORT).show()
            } else {
                goToHome(name)
            }
        }
    }

    private fun goToHome(name: String) {
        val intent = Intent(activity, Home::class.java).apply {
            val player1 = Player(name)
            this.putExtra("AN_OBJECT",player1)
        }
        startActivity(intent)
    }
}