package tw.tcnr10.m0501f
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import tw.tcnr10.m0501f.R

class M0501 : AppCompatActivity() {
    private var s001: Spinner? = null
    private var e001: EditText? = null
    private var b001: Button? = null
    private var f000: TextView? = null
    private var selectSex: String? = null
    private val iAge = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.m0501f)
        setupViewComponent()
    }

    private fun setupViewComponent() {
        s001 = findViewById<View>(R.id.m0501_s001) as Spinner
        e001 = findViewById<View>(R.id.m0501_e001) as EditText
        b001 = findViewById<View>(R.id.m0501_b001) as Button
        f000 = findViewById<View>(R.id.m0501_f000) as TextView
        //設定spinner item選項
        val adapSexList = ArrayAdapter.createFromResource(
            this,
            R.array.m0501_a001,
            R.layout.support_simple_spinner_dropdown_item
        ) //設定Spinner樣式
        adapSexList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        s001!!.adapter = adapSexList
        s001!!.onItemSelectedListener = s001ON
        //設定Spinner監聽
        b001!!.setOnClickListener(b001ON)
    }

    //==============================================================================================
    private val s001ON: OnItemSelectedListener = object : OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>,
            view: View,
            position: Int,
            id: Long
        ) {
            selectSex = parent.selectedItem.toString()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    private val b001ON =
        View.OnClickListener {
            var strSug = getString(R.string.m0501_f000)
            // 檢查 年齡是否有輸入
            if (e001!!.text.toString().trim { it <= ' ' }.length != 0) {
                val iAge = e001!!.text.toString().toInt()
                strSug += if (selectSex == getString(R.string.chkSex)) if (iAge < 28) getString(
                    R.string.m0501_f001
                ) else if (iAge > 33) getString(R.string.m0501_f003) else {
                    getString(R.string.m0501_f002)
                } else if (iAge < 25) getString(R.string.m0501_f001) else if (iAge > 30) getString(
                    R.string.m0501_f003
                ) else {
                    getString(R.string.m0501_f002)
                }
                f000!!.text = strSug
                //-------------------------------------------------------
            } else {
                strSug = getString(R.string.m0501_f004) //else { //年齡輸入空白
            }
            f000!!.text = strSug //請勿輸入空白
        }
}