package ru.lesson.fragmentsample.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.lesson.fragmentsample.R
import ru.lesson.fragmentsample.databinding.ActivityMainBinding
import ru.lesson.fragmentsample.presentation.recycler.RecyclerFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, RecyclerFragment())
                .commit()

    }
}
