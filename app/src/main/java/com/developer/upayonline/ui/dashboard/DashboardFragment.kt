package com.developer.upayonline.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developer.upay.others.Settings
import com.developer.upayonline.R
import com.developer.upayonline.adapters.HistoryAdapter
import com.developer.upayonline.adapters.OnHistoryItemClickListner
import com.developer.upayonline.models.Transaction
import com.developer.upayonline.others.CURL
import com.developer.upayonline.requests.JSONPlaceHolderApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class DashboardFragment : Fragment(), OnHistoryItemClickListner {

    private lateinit var dashboardViewModel: PaymentViewModel

    var recyclerView: RecyclerView? = null
    var historyAdapter: HistoryAdapter? = null
    lateinit var settings: Settings
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProviders.of(this).get(PaymentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        settings = Settings(this.requireContext())
        recyclerView = root.findViewById(R.id.recycler)

        getTransactionsRequest()

        return root
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
                        historyAdapter = HistoryAdapter(users, this@DashboardFragment)
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

    }
}