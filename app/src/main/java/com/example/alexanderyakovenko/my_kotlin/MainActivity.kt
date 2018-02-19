package com.example.alexanderyakovenko.my_kotlin

import android.app.DownloadManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import okhttp3.*
import java.io.IOException

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONStringer;
import com.google.gson.JsonSerializer;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonDeserializer;
import com.google.gson.GsonBuilder;


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)



        val b = maxOf(3,5)
        println("maxOf")
        println(b)

        fetchJson()

        button123.setOnClickListener {
            textView.setText("hello")
            textView.setBackgroundColor(Color.GRAY)
        }

        button2.setOnClickListener {
            button.setBackgroundColor(Color.GREEN)
            textView.setText("worked")

        }



        _dynamic_listView.adapter = MyCustomAdapter(this)




    }

    fun fetchJson() {
        println("Attempting to Fetch JSON")
        var url = "https://api.letsbuildthatapp.com/youtube/home_feed"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val  body = response?.body()?.string()
                println("RESPONSE BODY")
                println(body)
                val gson = GsonBuilder().create()
                var homeFeed = gson.fromJson(body, HomeFeed::class.java)


            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Error exequte request")
            }


        })
    }


    fun maxOf(a: Int, b: Int): Int {
        if (a > b) {
            return a
            println("aaaaaaaaaaaaa")
            println(a)
        } else {
            return b
            println("bbbbbbbbbbbbb")
            println(b)
        }
    }

    private class MyCustomAdapter(context: Context): BaseAdapter() {

        private val mContext: Context

        init {
            mContext = context
        }

        override fun getCount(): Int {
            return 5
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


            val numbers: IntArray = intArrayOf(10, 20, 30, 40, 50)

            val words = arrayOf("a", "b", "c", "d", "e")

            val textView = TextView(mContext)
            textView.text = "HERE is my ROW for my LISTVIEW" + "  " + "$position"  + "  " + numbers[position] + "  " + words[position]
            return textView


        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun request() {

        print("djwbfjhfbwfbjhbfawjbfsabcjhd")
        var adress = Adress()
        print("point stop")
        print(adress.name)
        print(adress.street)

        println("value" )
        println(adress.name)

        System.out.println(adress.name)
        System.out.println(adress.street)


    }

}

class HomeFeed(val videos: List<Video>)
class Video(val id: Int, val name: String)

public  class Adress {
    public var name: String? = "value 11111111111111"
    public var street: String = "value 11111111111487305086837658111"
}
