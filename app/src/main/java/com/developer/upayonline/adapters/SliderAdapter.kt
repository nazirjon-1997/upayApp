package com.developer.upay.adapters

/**
 * Created by User on 27.04.2018.
 */
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.developer.upay.models.SliderModel
import com.developer.upayonline.R
import java.util.*

class SliderAdapter(
    val context: Context,
    images: ArrayList<SliderModel>
) :
    PagerAdapter() {
    private val images: ArrayList<SliderModel>
    private val inflater: LayoutInflater
    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val myImageLayout: View = inflater.inflate(R.layout.slider, view, false)
        val myImage =
            myImageLayout.findViewById<View>(R.id.image) as ImageView
        val myTetx =
            myImageLayout.findViewById<View>(R.id.mealName) as TextView
        // myTetx.setText(images.get(position).getTitle());
        myImage.setImageResource(images[position].image)
        view.addView(myImageLayout, 0)
        return myImageLayout
        // Do something for froyo and above versions
    }

    override fun isViewFromObject(
        view: View,
        `object`: Any
    ): Boolean {
        return view == `object`
    }

    init {
        this.images = images
        inflater = LayoutInflater.from(context)
    }
}