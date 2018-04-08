package lunchcase.com.lunchcase.Fragment


import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_add_customer.*
import kotlinx.android.synthetic.main.fragment_add_customer.view.*
import lunchcase.com.lunchcase.Abstract.LunchCaseDatabase
import lunchcase.com.lunchcase.Data.AddUser


import lunchcase.com.lunchcase.R
import lunchcase.com.lunchcase.Thread.DbWorkerThread
import org.jetbrains.anko.support.v4.toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AddCustomerFragment : Fragment() {
    var nameData: String = ""
    var phoneData: String = ""
    var emailData: String = ""
    var addressData: String = ""

    private var mDb: LunchCaseDatabase? = null
    private lateinit var mDbWorkerThread: DbWorkerThread
    private val mUiHandler = Handler()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_add_customer, container, false)
        mDb = LunchCaseDatabase.getInstance(context)
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
        submitClick(view)
        return view
    }

    private fun submitClick(view: View) {

        view.btn_submit.setOnClickListener {
            var userData = AddUser()
            nameData = name.text.toString()
            phoneData = phone.text.toString()
            emailData = email.text.toString()
            addressData = address.text.toString()
            userData.name = nameData
            userData.phone = phoneData
            userData.email = emailData
            userData.address = addressData



            insertCustomerDataInDb(userData)
        }

    }

    private fun insertCustomerDataInDb(user: AddUser) {
        val task = Runnable { mDb?.customerDataDao()?.insertCustomerRecord(user) }
        mDbWorkerThread.postTask(task)

    }

    private fun fetchWeatherDataFromDb() {
        val task = Runnable {
            val weatherData =
                    mDb?.customerDataDao()?.getAllUser()
            mUiHandler.post({
                if (weatherData == null || weatherData?.size == 0) {
                    toast("no data")
                } else {
                    toast(weatherData?.get(0).name)

                }
            })
        }
        mDbWorkerThread.postTask(task)
    }

}
