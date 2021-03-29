package tw.tcnr10.m0500f

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class M0500 : AppCompatActivity() {
    private var e001: EditText? = null
    private var b001: Button? = null
    private var t003: TextView? = null
    private val b001ON =
        View.OnClickListener {
            val currecyFormat = DecimalFormat("0.0000")
            val output =
                currecyFormat.format(e001!!.text.toString().toFloat() * 28.6)
            t003!!.text = output
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.m0500f)
        setupViewComponent()
    }

    private fun setupViewComponent() {
        e001 = findViewById(R.id.m0500_e001)
        b001 = findViewById(R.id.m0500_b001) //公斤轉換磅的按鈕
        t003 = findViewById(R.id.m0500_t003) //輸出轉換後的磅數
        b001!!.setOnClickListener(b001ON)//宣告按鈕的監聽程式

    }

//    private fun setupViewComponent() {
////        設定layout配置
//        e001 = findViewById<View>(R.id.m0500_e001) as EditText //輸入公斤的欄位
//        b001 = findViewById<View>(R.id.m0500_b001) as Button //公斤轉換磅的按鈕
//        t003 = findViewById<View>(R.id.m0500_t003) as TextView //輸出轉換後的磅數
//        b001!!.setOnClickListener(b001ON) //宣告按鈕的監聽程式
//    }

    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.m0500);
    //    }
}