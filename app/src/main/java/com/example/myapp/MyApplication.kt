package com.example.myapp

import android.app.Application
import com.example.myapp.model.DBOpenHelper
import com.example.myapp.model.Model
import com.example.myapp.model.Repository
import com.example.myapp.presenter.*
import com.example.myapp.utils.RealRunner


class MyApplication : Application(), PresenterHolder {

    lateinit var mAddUserPresenter: AddUserPresenter
    lateinit var mMainPresenter: MainPresenter
    lateinit var mResultPresenter: ResultPresenter
    lateinit var mPreviousCheckPresenter: PreviousCheckPresenter
    lateinit var mScanPresenter: ScanPresenter
    private val db = DBOpenHelper(this, null)
    val model = Model(db)
    private val rep = Repository()

    override fun getAddUserPresenter() = mAddUserPresenter

    override fun getMainPresenter() = mMainPresenter

    override fun getResultPresenter() = mResultPresenter
    override fun getPreviousCheckPresenter() = mPreviousCheckPresenter
    override fun getScanPresenter() = mScanPresenter

    override fun onCreate() {
        mAddUserPresenter = AddUserPresenter(model, RealRunner())
        mMainPresenter = MainPresenter(model, RealRunner(), rep)
        mResultPresenter = ResultPresenter(model, RealRunner())
        mPreviousCheckPresenter = PreviousCheckPresenter(model, RealRunner(), rep)
        mScanPresenter = ScanPresenter(model, RealRunner(), rep)
        super.onCreate()
    }

}