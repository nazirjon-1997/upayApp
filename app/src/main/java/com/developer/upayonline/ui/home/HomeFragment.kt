package com.developer.upayonline.ui.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.developer.upay.adapters.SliderAdapter
import com.developer.upay.models.SliderModel
import com.developer.upay.others.Settings
import com.developer.upay.others.SliderData
import com.developer.upayonline.R
import com.developer.upayonline.adapters.HistoryAdapter
import com.developer.upayonline.adapters.OnHistoryItemClickListner
import com.developer.upayonline.models.Transaction
import com.developer.upayonline.models.User
import com.developer.upayonline.others.CURL
import com.developer.upayonline.requests.JSONPlaceHolderApi
import me.relex.circleindicator.CircleIndicator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment(), OnHistoryItemClickListner {

    private lateinit var homeViewModel: HomeViewModel
    private val TAG = "LoginLog"
    private val mobile: ImageButton? = null
    private  var bank:ImageButton? = null
    private  var wallet:ImageButton? = null
    private var mPager: ViewPager? = null
    private var array: ArrayList<SliderModel>? = null
    private var currentPage = 0
    var recyclerView: RecyclerView? = null
    var historyAdapter: HistoryAdapter? = null
    lateinit var settings: Settings
    var balance: TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        settings = Settings(this.requireContext())
        balance = root.findViewById(R.id.all_balance)
        recyclerView = root.findViewById(R.id.recycler);

        array = ArrayList()

        for (i in 0 until SliderData.img.size) {
            array!!.add(
                SliderModel(
                    SliderData.img.get(i),  //getStringResourceByName(SlideData.text[i]),
                    SliderData.id_.get(i)
                )
            )
        }

        mPager = root.findViewById(R.id.viewPage)
        mPager?.setAdapter(SliderAdapter(requireContext(), array!!))
        val indicator = root.findViewById(R.id.indicator) as CircleIndicator
        indicator.setViewPager(mPager)
        // Auto start of viewpager
        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage === SliderData.img.size) {
                currentPage = 0
            }
            mPager?.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 7500, 7500)
        getCustomInfoRequest()
        getTransactionsRequest()
        return root
    }

    private fun getCustomInfoRequest() {
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.addInterceptor(logging) // <-- this is the important line!
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(CURL.SERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        val client =
            retrofit.create(JSONPlaceHolderApi::class.java)
        val calltargetResponse: Call<User> =
            client.getCustomerInfo("Bearer " + settings.token)
        calltargetResponse.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>
            ) {
                if (response.isSuccessful) {
                    val user: User? = response.body()
                    if (!user?.getBalance().toString().isEmpty()) {
                        balance?.setText(user?.getBalance().toString() + " TJS");
                    }
                } else {
                    try {
                        Log.e("stag", response.errorBody().string())
                    } catch (e: IOException) {
                        Log.e("stag", e.message!!)
                    }
                }
            }
            override fun onFailure(
                call: Call<User>,
                t: Throwable?
            ) {
                Toast.makeText(activity, "Failed ", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getTransactionsRequest() {
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging) // <-- this is the important line!
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(CURL.SERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        val client =
            retrofit.create(JSONPlaceHolderApi::class.java)
        val calltargetResponse: Call<List<Transaction>> =
            client.getTransactions("Bearer " + settings.token)
        calltargetResponse.enqueue(object : Callback<List<Transaction>> {
            override fun onResponse(
                call: Call<List<Transaction>>,
                response: Response<List<Transaction>>
            ) {
                val users = ArrayList<Transaction>()
                if (response.isSuccessful) {
                    val users = ArrayList<Transaction>()
                    for (trans in response.body()) {
                        val serviceId: Int? = trans.getServiceid()
                        val serviceName: String? = trans.getServiceName()
                        val account: String? = trans.getAccount()
                        val amount: Double? = trans.getAmount()
                        val status: Int? = trans.getStatus()
                        val dateTime: String? = trans.getDateTime()

                        users.add(
                            Transaction(
                                serviceId,
                                serviceName,
                                account,
                                amount,
                                status,
                                dateTime
                            )
                        )
                        historyAdapter = HistoryAdapter(users, this@HomeFragment)
                    }
                } else {
                    try {
                        Log.e("stag", response.errorBody().string())
                    } catch (e: IOException) {
                        Log.e("stag", e.message!!)
                    }
                }
                val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(activity!!.applicationContext, 1)
                recyclerView!!.layoutManager = mLayoutManager
                recyclerView!!.adapter = historyAdapter
            }

            override fun onFailure(
                call: Call<List<Transaction>>,
                t: Throwable
            ) {
                Toast.makeText(activity, "Failed ", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemClick(item: Transaction, position: Int) {
        TODO("Not yet implemented")
    }


}