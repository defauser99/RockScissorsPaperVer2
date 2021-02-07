package binar.ch4.rockscissorspaper2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import binar.ch4.rockscissorspaper2.databinding.ActivitySplashScreenBinding
import com.bumptech.glide.Glide

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },2000)
        uploadImage("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
    }

    private fun uploadImage(url: String){
        Glide.with(this)
            .load(url)
            .into(binding.splashScreenTitle)
    }
}