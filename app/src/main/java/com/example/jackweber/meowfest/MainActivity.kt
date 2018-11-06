package com.example.jackweber.meowfest

import android.app.ProgressDialog
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var catAdapter: CatAdapter
    private lateinit var recyclerView: RecyclerView
    var count = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeCatCall(0)

    }

    fun makeCatCall(page: Int){
        var progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.show()

        var server = RetroInstance.getInstance().create(RetroInterface::class.java)
        var call = server.getCats(page.toString())
        Log.d("URL", call.request().url().toString())
        call.enqueue(object : Callback<List<RetroCat>> {
            override fun onResponse(call: Call<List<RetroCat>>?, response: Response<List<RetroCat>>?) {
                progressDialog.dismiss()
                var body = response?.body()
                if (body != null) {
                    generateData(body)
                }
            }

            override fun onFailure(call: Call<List<RetroCat>>?, t: Throwable?) {
                progressDialog.dismiss()
                Log.d("Error", "Not loaded")
            }
        })
    }

    fun loadNext(view: View) {
        var backButton = findViewById<Button>(R.id.backButton)
        count++
        makeCatCall(count)
        backButton.visibility = View.VISIBLE
    }

    fun loadBack(view: View){
        count--
        if(count <= 0){
            var backButton = findViewById<Button>(R.id.backButton)
            backButton.visibility = View.GONE
        }
        makeCatCall(count)
    }

    fun generateData(list: List<RetroCat>) {
        recyclerView = findViewById(R.id.rv_1)
        catAdapter = CatAdapter(list, this)
        var layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = catAdapter
    }
}
