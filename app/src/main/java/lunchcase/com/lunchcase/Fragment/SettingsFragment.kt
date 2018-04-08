package lunchcase.com.lunchcase.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*
import lunchcase.com.lunchcase.Abstract.LunchCaseDatabase
import lunchcase.com.lunchcase.Data.AddPrice

import lunchcase.com.lunchcase.R
import lunchcase.com.lunchcase.Thread.DbWorkerThread

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SettingsFragment : Fragment() {
    var singleTime: String = ""
    var twoTime: String = ""
    var threeTime: String = ""
    private var mDb: LunchCaseDatabase? = null
    private lateinit var mDbWorkerThread: DbWorkerThread
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_settings, container, false)
        mDb = LunchCaseDatabase.getInstance(context)
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
        submitClick(view)
        return view
    }

    private fun submitClick(view: View) {
        view.btn_price.setOnClickListener {
            var priceData = AddPrice()
            threeTime = edt_3time.text.toString()
            twoTime = edt_2time.text.toString()
            singleTime = edt_1time.text.toString()
                       priceData.singleTime = singleTime
            priceData.id=1
            priceData.twoTime = twoTime
            priceData.threeTime = threeTime
            insertPriceInDb(priceData)
        }
    }

    private fun insertPriceInDb(priceData: AddPrice) {
        val task = Runnable { mDb?.customerDataDao()?.insertPriceRecord(priceData) }
        mDbWorkerThread.postTask(task)
    }


}
