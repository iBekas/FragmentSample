package ru.lesson.fragmentsample.presentation

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.lesson.fragmentsample.R
import ru.lesson.fragmentsample.app.App
import ru.lesson.fragmentsample.databinding.ActivityMainBinding
import ru.lesson.fragmentsample.databinding.DialogSettingsBinding
import ru.lesson.fragmentsample.presentation.recycler.RecyclerFragment

private const val THEME_CODE = "THEME_CODE"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Устанавливаем тему из SharedPreferences
        setTheme(App.getSettings().getInt(THEME_CODE, R.style.Theme_FragmentSample))

        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, RecyclerFragment())
                .commit()

    }


    //Создаем верхнее меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    //Указываем действие для элементов меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                showSettingsDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSettingsDialog() {
        val bindingMissed =
            DialogSettingsBinding.inflate(LayoutInflater.from(this), null, false)

        val attentionDialog = MaterialAlertDialogBuilder(this)
            .setCustomTitle(bindingMissed.root)
            .setCancelable(true)
            .setBackground(ColorDrawable(ContextCompat.getColor(this, R.color.transparent)))
            .create()

        bindingMissed.firstTheme.setOnClickListener {
            //Сохраняем код темы в SharedPreferences
            App.getSettings().edit().putInt(THEME_CODE, R.style.Theme_FragmentSample).apply()
            attentionDialog.dismiss()
            recreate()
        }
        bindingMissed.secondTheme.setOnClickListener {
            App.getSettings().edit().putInt(THEME_CODE, R.style.SecondTheme).apply()
            attentionDialog.dismiss()
            recreate()
        }

        attentionDialog.show()
    }
}
