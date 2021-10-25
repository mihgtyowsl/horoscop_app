package com.example.horoscop_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class horoscop : AppCompatActivity() {
    //Lager en global variabel for request-kø
    var requestQueue : RequestQueue? = null
    var dateRange : TextView? = null
    var todayDate : TextView? = null
    var desc : TextView? = null
    var compability : TextView? = null
    var mood : TextView? = null
    var color : TextView? = null
    var lucky_number : TextView? = null
    var lucky_time : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horoscop)
        val textView = findViewById<TextView>(R.id.main)
        APIkall(textView)
        dateRange = findViewById(R.id.dateRange)
        todayDate = findViewById(R.id.todayDate)
        desc = findViewById(R.id.desc)
        compability = findViewById(R.id.compability)
        mood = findViewById(R.id.mood)
        color = findViewById(R.id.color)
        lucky_number = findViewById(R.id.lucky_number)
        lucky_time = findViewById(R.id.lucky_time)



    }



    private fun APIkall(view: TextView) {
        requestQueue = Volley.newRequestQueue(this)
        val url = "https://aztro.sameerkumar.website/?sign=aries&day=today"

        //Lager en request
        val request = StringRequest(
            Request.Method.POST, url,
            { response ->
                //Gjør noe med svaret
                val formatted = formatResponse(response)

            },
            { error ->
                //Håndterer eventuelle feil
                Log.d("error", error.toString())
                view.text = "Kunne ikke laste horoskop!"
            }
        )
        request.retryPolicy =
            DefaultRetryPolicy(10 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

        //Gir requesten en tag
        request.tag = "horoskop"

        //Legger til requesten i køen
        requestQueue?.add(request)
    }






    override fun onStop() {
        super.onStop()
        requestQueue?.cancelAll("horoskop")
    }


    private fun formatResponse(resp : String) : List<String> {
        resp.drop(0)
        resp.dropLast(1)
        val liste = resp.split("\"")
        dateRange!!.text = liste[3]
        todayDate!!.text = liste[7]
        desc!!.text = liste[11]
        compability!!.text = liste[15]
        mood!!.text = liste[19]
        color!!.text = liste[23]
        lucky_number!!.text = liste[27]
        lucky_time!!.text = liste[31]

        return liste

    }




}