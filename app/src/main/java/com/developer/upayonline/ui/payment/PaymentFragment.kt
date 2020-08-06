package com.developer.upayonline.ui.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developer.upayonline.R
import com.developer.upayonline.ServicesActivity
import com.developer.upayonline.adapters.GroupAdapter
import com.developer.upayonline.adapters.OnItemClickListner
import com.developer.upayonline.models.GroupModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*


class PaymentFragment : Fragment(), OnItemClickListner {

    private lateinit var paymentViewModel: PaymentViewModel
    lateinit var recyclerView: RecyclerView
    var groupAdapter: GroupAdapter? = null
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        paymentViewModel =
                ViewModelProviders.of(this).get(PaymentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_payment, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        paymentViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        recyclerView = root.findViewById(R.id.recycler)

        val groups: ArrayList<GroupModel> = ArrayList<GroupModel>()
        try {
            val jsonobject = JSONObject(getJsonDataFromAsset(context!!, "groups.json"))
            Log.d("AA", jsonobject.toString())
            val jarray1 = jsonobject.getJSONArray("Cat1s") as JSONArray
            for (i in 0 until jarray1.length()) {
                val jb = jarray1[i] as JSONObject
                val lang = jb.getString("LangKey")
                val value = jb.getString("Value")
                if (lang == "ru") {
                    groups.add(GroupModel(1, value, "Cat1s"))
                }
            }
            val jarray2 = jsonobject.getJSONArray("Cat2s") as JSONArray
            for (i in 0 until jarray2.length()) {
                val jb = jarray2[i] as JSONObject
                val lang = jb.getString("LangKey")
                val value = jb.getString("Value")
                if (lang == "ru") {
                    groups.add(GroupModel(2, value, "Cat2s"))
                }
            }
            val jarray3 = jsonobject.getJSONArray("Cat3s") as JSONArray
            for (i in 0 until jarray3.length()) {
                val jb = jarray3[i] as JSONObject
                val lang = jb.getString("LangKey")
                val value = jb.getString("Value")
                if (lang == "ru") {
                    groups.add(GroupModel(3, value, "Cat3s"))
                }
            }
            val jarray4 = jsonobject.getJSONArray("Cat4s") as JSONArray
            for (i in 0 until jarray4.length()) {
                val jb = jarray4[i] as JSONObject
                val lang = jb.getString("LangKey")
                val value = jb.getString("Value")
                if (lang == "ru") {
                    groups.add(GroupModel(4, value, "Cat4s"))
                }
            }
            val jarray5 = jsonobject.getJSONArray("Cat5s") as JSONArray
            for (i in 0 until jarray5.length()) {
                val jb = jarray5[i] as JSONObject
                val lang = jb.getString("LangKey")
                val value = jb.getString("Value")
                if (lang == "ru") {
                    groups.add(GroupModel(5, value, "Cat5s"))
                }
            }
            val jarray6 = jsonobject.getJSONArray("Cat6s") as JSONArray
            for (i in 0 until jarray6.length()) {
                val jb = jarray6[i] as JSONObject
                val lang = jb.getString("LangKey")
                val value = jb.getString("Value")
                if (lang == "ru") {
                    groups.add(GroupModel(6, value, "Cat6s"))
                }
            }
            val jarray7 = jsonobject.getJSONArray("Cat7s") as JSONArray
            for (i in 0 until jarray7.length()) {
                val jb = jarray7[i] as JSONObject
                val lang = jb.getString("LangKey")
                val value = jb.getString("Value")
                if (lang == "ru") {
                    groups.add(GroupModel(7, value, "Cat7s"))
                }
            }
            val jarray8 = jsonobject.getJSONArray("Cat8s") as JSONArray
            for (i in 0 until jarray8.length()) {
                val jb = jarray8[i] as JSONObject
                val lang = jb.getString("LangKey")
                val value = jb.getString("Value")
                if (lang == "ru") {
                    groups.add(GroupModel(8, value, "Cat8s"))
                }
            }
            val mLayoutManager: RecyclerView.LayoutManager =
                GridLayoutManager(activity!!.applicationContext, 1)
            recyclerView.setLayoutManager(mLayoutManager)
            groupAdapter = GroupAdapter(groups, this)
            recyclerView.setAdapter(groupAdapter)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return root
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    override fun onItemClick(item: GroupModel, position: Int) {
        Toast.makeText(context, item.name , Toast.LENGTH_SHORT).show()
        val intent = Intent(context, ServicesActivity::class.java)
        intent.putExtra("group", item.category)
        startActivity(intent)
    }
}