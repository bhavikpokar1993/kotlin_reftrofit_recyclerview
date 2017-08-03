package com.sprybit.app

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import com.sprybit.app.adpater.ContentAdapter
import com.sprybit.app.util.APIInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    var mContext = null;
    var tag = "Main";


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*list.layoutManager = LinearLayoutManager(this)
    list.hasFixedSize()
    list.adapter = Myadpater(this, getLists())*/

        val recycler = findViewById(R.id.recycler) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.hasFixedSize()
//        recycler.layoutManager = GridLayoutManager(this, 2)


        /*val txtHello = findViewById(R.id.txtHello) as TextView*/
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
                .baseUrl("http://52.14.67.157/5facts/MVC/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        var redditApi = retrofit.create(APIInterface::class.java)

        val request = "get_qoute"
        val callQuote = redditApi.getAllQuote("2017-05-25", request)
        callQuote.enqueue(object : Callback<List<Quote>> {
            override fun onResponse(call: Call<List<Quote>>, response: Response<List<Quote>>) {
                Log.d(tag, "response Code [" + response.code() + " ]")
                /*Toast.makeText(applicationContext, "Name of text [" + txtHello.text + " ]", Toast.LENGTH_LONG).show();
                txtHello.setText(response.message());*/
//                adapter.items = response.body()!!
                var listQuotes: List<Quote> = response.body()!!
                listQuotes += response.body()!!
                listQuotes += response.body()!!
                listQuotes += response.body()!!
                listQuotes += response.body()!!
                listQuotes += response.body()!!
                listQuotes += response.body()!!
                listQuotes += response.body()!!
                listQuotes += response.body()!!

                recycler.adapter = ContentAdapter(listQuotes)
            }

            override fun onFailure(call: Call<List<Quote>>, t: Throwable) {
                Log.d(tag, "response Failed")
            }
        })
        /*txtHello.setOnClickListener {
            Toast.makeText(applicationContext, "Name of text [" + txtHello.text + " ]", Toast.LENGTH_LONG).show();
            GetWeatherTask(txtHello, applicationContext).execute()

        }*/


    }

    class GetWeatherTask(textView: TextView, context: Context) : AsyncTask<Void, Void, String>() {

        val innerTextView: TextView? = textView
        val mContext: Context? = context

        override fun doInBackground(vararg params: Void?): String? {
            val url = URL("https://raw.githubusercontent.com/irontec/android-kotlin-samples/master/common-data/bilbao.json")
            val httpClient = url.openConnection() as HttpURLConnection
            if (httpClient.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try {
                    val stream = BufferedInputStream(httpClient.getInputStream())
                    val data: String = readStream(inputStream = stream)
                    return data;
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    httpClient.disconnect()
                }
            } else {
                println("ERROR ${httpClient.getResponseCode()}")
            }
            return null
        }

        fun readStream(inputStream: BufferedInputStream): String {
            val bufferedReader = BufferedReader(InputStreamReader(inputStream));
            val stringBuilder = StringBuilder();
            bufferedReader.forEachLine { stringBuilder.append(it) }
            return stringBuilder.toString()
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            Log.d("MainAct", result)

            innerTextView?.setText(JSONObject(result).toString())

            var activityIntent = Intent(mContext, MapsActivity::class.java)
            activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (mContext != null) {
                mContext.startActivity(activityIntent)
            }
            /**
             * ... Work with the weather data
             */

        }
    }
}
