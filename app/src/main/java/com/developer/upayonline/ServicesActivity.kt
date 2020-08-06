package com.developer.upayonline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.developer.upayonline.adapters.OnServiceItemClickListner
import com.developer.upayonline.adapters.ServiceAdapter
import com.developer.upayonline.models.ServiceModel


class ServicesActivity : AppCompatActivity(), OnServiceItemClickListner {

    var recyclerView: RecyclerView? = null
    var serviceAdapter: ServiceAdapter? = null
    var group: String? = null

    private val TAG = "Service"
    var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)
        recyclerView = findViewById(R.id.recycler)
        toolbar = findViewById(R.id.my_toolbar)
        //toolbar.setTitle("")
        setSupportActionBar(toolbar)
        val extras = intent.extras
        if (extras != null) {
            group = extras.getString("group") // получение категория группа
        }
    }

    override fun onItemClick(item: ServiceModel, position: Int) {

    }

}