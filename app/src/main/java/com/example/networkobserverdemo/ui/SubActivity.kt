package com.example.networkobserverdemo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.networkobserverdemo.R
import com.example.networkobserverdemo.databinding.ActivitySubBinding
import com.example.networkobserverdemo.viewmodel.SubViewModel

/**
 * 想定する業務要件を満たした時に強制的に起動し、戻れないActivity
 *
 * バックボタンを押すと、アプリを終了する
 * すでに最前面に表示中の場合は、新しいインスタンスは作成しない
 * 起動モードは [Intent.FLAG_ACTIVITY_SINGLE_TOP]、ただしIntent.flagではなく AndroidManifest で定義
 */
class SubActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "Sub" + " mori"
    }

    private lateinit var viewModel: SubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate, $this")

        viewModel = ViewModelProvider.NewInstanceFactory().create(SubViewModel::class.java)
        val binding =
            DataBindingUtil.setContentView<ActivitySubBinding>(this, R.layout.activity_sub)
        binding.viewModel = viewModel

        setupRestartMainActivity()
        Toast.makeText(this, R.string.view_created, Toast.LENGTH_LONG).show()
    }

    private fun setupRestartMainActivity() {
        viewModel.positiveButtonClick.observe(this) { clicked ->
            Log.d(TAG, "positiveButtonClick: $clicked")
            if (clicked) {
                startMainActivityWithClearingTask()
            }
        }
    }

    private fun startMainActivityWithClearingTask() {
        val intent = Intent(this, MainActivity::class.java).apply {
            // 既存のスタックを全て削除し、MainActivityを作り直す
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java).apply {
            // onNewIntentでこのアクションを受け取るとMainActivity（ルートアクティビティ）はfinishする、すなわちアプリ終了
            action = INTENT_ACTION_FINISH_APP
            // MainActivityの上にあるスタックをクリア、そして新しいインスタンスは作らずonNewIntentで受け取らせる
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        startActivity(intent)
    }
}