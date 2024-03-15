package com.example.a40kcc

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class PlayerAddActivity : AppCompatActivity() {

    private lateinit var editPlayerView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_data)
        editPlayerView = findViewById(R.id.new_data)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editPlayerView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editPlayerView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.PlayerAdd.REPLY"
    }
}