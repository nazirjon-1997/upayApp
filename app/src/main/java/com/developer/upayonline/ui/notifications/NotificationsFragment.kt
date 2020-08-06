package com.developer.upayonline.ui.notifications

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.preference.Preference
import androidx.preference.PreferenceFragment
import androidx.preference.PreferenceScreen
import com.developer.upayonline.LoginActivity
import com.developer.upayonline.R
import com.developer.upayonline.UserActivity


class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        requireActivity().fragmentManager.beginTransaction()
            .replace(R.id.container_settings, SettingsFragment())
            .commit()
        return root
    }

    class SettingsFragment : PreferenceFragment(),
        Preference.OnPreferenceChangeListener {
        var profile: PreferenceScreen? = null
        var out: PreferenceScreen? = null
        var about: PreferenceScreen? = null
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.pref)
            profile = findPreference("profil") as PreferenceScreen?
            out = findPreference("out") as PreferenceScreen?
            profile!!.setOnPreferenceClickListener(object : Preference.OnPreferenceClickListener {
                override fun onPreferenceClick(preference: Preference?): Boolean {
                    startActivity(Intent(activity, UserActivity::class.java))
                    return false
                }
            })
            out!!.setOnPreferenceClickListener(object : Preference.OnPreferenceClickListener {
                override fun onPreferenceClick(preference: Preference?): Boolean {
                    outDialog()
                    return false
                }
            })
        }

        private fun outDialog() {
            val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(activity)
            builder.setTitle("Выйти из UPay?")
            //builder.setMessage(String.format("Выйти из UPay?"));
            builder.setPositiveButton("Да",
                DialogInterface.OnClickListener { dialog, which ->
                    startActivity(Intent(activity, LoginActivity::class.java))
                })
            builder.setNegativeButton("Нет",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            builder.setCancelable(false)
            builder.create()
            builder.show()
        }

        @SuppressLint("ResourceAsColor")
        override fun onViewCreated(
            view: View,
            @Nullable savedInstanceState: Bundle?
        ) {
            super.onViewCreated(view, savedInstanceState)
            getListView().setBackgroundColor(getResources().getColor(R.color.back_color))
        }

        override fun onCreatePreferences(
            savedInstanceState: Bundle?,
            rootKey: String?
        ) {
        }

        override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
            return false
        }

        companion object {
            var TAG = SettingsFragment::class.java.simpleName
        }
    }

}