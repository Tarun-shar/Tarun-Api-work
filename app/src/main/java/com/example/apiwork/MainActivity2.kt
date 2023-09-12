package com.example.apiwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.apiwork.databinding.ActivityMain2Binding
import org.json.JSONObject

class MainActivity2 : AppCompatActivity() {

    val url:String = "https://api.slingacademy.com/v1/sample-data/photos"

    private lateinit var adapter: ItemAdapter
    private lateinit var dataList: ArrayList<ModelClass>

    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        dataList = ArrayList()

        getApiData()

//        adapter.setOnClickListener(object :
//            ItemAdapter.OnClickListener {
//            override fun onClick(position: Int, model: ModelClass) {
//                Toast.makeText(this@MainActivity2, "clicked", Toast.LENGTH_SHORT).show()
////                val intent = Intent(this, EmployeeDetails::class.java)
////                // Passing the data to the
////                // EmployeeDetails Activity
////                intent.putExtra(NEXT_SCREEN, model)
////                startActivity(intent)
//            }
//        })

    }

    private fun getApiData() {

            val queue = Volley.newRequestQueue(this)

            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->

                    val responseObject = JSONObject(response)

                    val jsonArray = responseObject.getJSONArray("photos")

                    try{
                        for (i in 0 until jsonArray.length()){
                            if(i%2 == 1){
                                val jsonObject1 = jsonArray.getJSONObject(i)

                                val titleObj= jsonObject1.get("title").toString()

                                val userObj = jsonObject1.get("user").toString()

                                val imgObj = jsonObject1.get("url").toString()

                                dataList.add(ModelClass(imgObj,titleObj,userObj))

                                adapter = ItemAdapter(dataList,this)

                                binding.recycleViewId.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

                                binding.recycleViewId.adapter = adapter
                            }
                        }
                    }
                    catch (e:Exception){
                        e.printStackTrace()
                    }

                },
                { error ->
                    Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
                })

// Add the request to the RequestQueue.
            queue.add(stringRequest)

    }
}