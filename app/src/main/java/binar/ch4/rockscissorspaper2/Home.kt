package binar.ch4.rockscissorspaper2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import binar.ch4.rockscissorspaper2.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val player1 = intent.getParcelableExtra<Player>("AN_OBJECT")
        val name = player1?.name.toString()
        val fragment = MenuFragment.newInstance(name,"")

        supportFragmentManager.beginTransaction().apply {
            add(binding.container.id, fragment)
            setReorderingAllowed(true)
            commit()
        }
    }
}