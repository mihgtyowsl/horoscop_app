package com.example.horoscop_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class MainActivity : Activity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val signSinner: Spinner = findViewById(R.id.signSpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.sign,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            signSinner.adapter = adapter
        }
        val daySinner: Spinner = findViewById(R.id.daySpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.day,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            daySinner.adapter = adapter
        }
    val start : Button =findViewById(R.id.startActivity)

        start.setOnClickListener {
            val sign : String = signSinner.getSelectedItem().toString()
            val dag : String = daySinner.getSelectedItem().toString()
            val intent = Intent(this, horoscop::class.java)
            intent.putExtra("url",createURL(sign,dag))
            startActivity(intent)
        }


    }
    private fun createURL(sign : String, date: String): String {
        val url = "https://aztro.sameerkumar.website/?sign=$sign&day=$date"
        return url
    }


    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        parent.getItemAtPosition(pos)

    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }
}


