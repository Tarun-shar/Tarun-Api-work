package com.example.apiwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.apiwork.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val url:String = "https://api.slingacademy.com/v1/sample-data/photos"

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        binding.next.setOnClickListener {
            getData()
        }

    }

    private fun getData() {

        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->

                val responseObject = JSONObject(response)

                val jsonArray = responseObject.getJSONArray("photos")
//                for (i in 0..jsonArray.length()){
                    val jsonObject1 = jsonArray.getJSONObject(0)

                    val titleObj= jsonObject1.get("title").toString()
                    binding.title.text = titleObj

                    val autherObj = jsonObject1.get("user").toString()
                    binding.user.text =  autherObj

                    Glide.with(this).load(jsonObject1.get("url")).into(binding.imageView)
//

            },
            { error ->
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}




