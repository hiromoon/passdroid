package net.moon428.hiromu.passdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val editText: EditText = findViewById(R.id.editText)
        val text = editText.text
        val datePicker: DatePicker = findViewById(R.id.datePicker2)
        val date = datePicker.year.toString() + (datePicker.month+1).toString() + datePicker.dayOfMonth.toString()
        Toast.makeText(this, createHash(text.toString(), date), Toast.LENGTH_SHORT).show()
    }

    private fun createHash(password: String, date: String, length: Int = 8): String {
        val source = password + date
        val charset = StandardCharsets.UTF_8
        val algorithm = "SHA-512"

        val md = MessageDigest.getInstance(algorithm)
        md.update(source.toByteArray(charset))

        val string = buildString {
            for (b in md.digest()) {
                append(String.format("%02x", b))
            }
        }
        return string.substring(0..length)
    }
}
