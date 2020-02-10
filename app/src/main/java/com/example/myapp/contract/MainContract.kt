package com.example.myapp.contract

import android.widget.CheckBox
import com.example.myapp.model.Product
import com.example.myapp.model.User

interface MainContract {

    interface view {

    }

    interface presenter {
        fun showData(check_id : Long)
        fun addMoneyFromUser(users : ArrayList<User>, checkMap : HashMap<Pair<String, String>, ArrayList<CheckBox>>, check_id : Long)
        fun addOneMoreCheck(qrResult: String?)
    }
}