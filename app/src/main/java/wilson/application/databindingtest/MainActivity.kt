package wilson.application.databindingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import wilson.application.databindingtest.databinding.ActivityMainBinding
import wilson.application.databindingtest.models.User
import wilson.application.databindingtest.viewModels.GameViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mainBindingUtil: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBindingUtil = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainBindingUtil.gameViewModel = GameViewModel(mainBindingUtil.root)

    }
}