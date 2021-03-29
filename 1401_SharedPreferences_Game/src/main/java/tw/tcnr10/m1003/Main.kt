package tw.tcnr10.m1003

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main : AppCompatActivity(), View.OnClickListener {
    private val backBtn: Button? = null
    private val winStats: TextView? = null
    private val loseStats: TextView? = null
    private val drawStats: TextView? = null
    private val totalStats: TextView? = null
    private var launchBtn: Button? = null
    private var txtResult: TextView? = null
    private val LAUNCH_GAME = 0
    private var iCountPlayerWin = 0
    private var iCountComWin = 0
    private var iCountDraw = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        setupViewComponent()
    }

    private fun setupViewComponent() {
        launchBtn = findViewById<View>(R.id.btnLaunchAct) as Button
        txtResult = findViewById<View>(R.id.txtResult) as TextView
        launchBtn!!.setOnClickListener(this)
        loadAndSet()
    }

    override fun onClick(v: View) {
        val intent = Intent()
        intent.setClass(this, M1003::class.java)
        startActivityForResult(intent, LAUNCH_GAME) //呼叫程式有回傳值
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != LAUNCH_GAME) {
            return
        }
        when (resultCode) {
            Activity.RESULT_OK -> {
                val bundle = data!!.extras
                iCountPlayerWin = bundle!!.getInt("KEY_WIN")
                iCountComWin = bundle.getInt("KEY_LOSE")
                iCountDraw = bundle.getInt("KEY_DRAW")
                val s =
                    getString(R.string.m1004_result) + (iCountPlayerWin + iCountComWin + iCountDraw) + getString(
                        R.string.m1004_table
                    ) + "\n " +
                            getString(R.string.m1004_PlayerWin) + iCountPlayerWin + getString(R.string.m1004_table) + "\n " +
                            getString(R.string.m1004_comWin) + iCountComWin + getString(R.string.m1004_table) + "\n " +
                            getString(R.string.m1004_draw) + iCountDraw + getString(R.string.m1004_table)
                txtResult!!.text = s
                //++++++++++++++++++++++++++++++存檔+++++++++++++++++++++++++++++++++++
                val gameResultData = getSharedPreferences("GAME_RESULT", 0)
                gameResultData.edit()
                    .putInt("COUNT_WIN", iCountPlayerWin)
                    .putInt("COUNT_DRAW", iCountComWin)
                    .putInt(
                        "COUNT_LOSE",
                        iCountDraw
                    ) //                .putInt("COUNT_SET",iwin+ilose+wedraw)
                    .commit()
            }
            Activity.RESULT_CANCELED -> txtResult!!.text = getString(R.string.cancel)
        }
    }

    private fun loadAndSet() {
        val gameResultData = getSharedPreferences("GAME_RESULT", 0)
        iCountPlayerWin = gameResultData.getInt("COUNT_WIN", 0)
        iCountComWin = gameResultData.getInt("COUNT_DRAW", 0)
        iCountDraw = gameResultData.getInt("COUNT_LOSE", 0)
        val s =
            getString(R.string.m1004_result) + (iCountPlayerWin + iCountComWin + iCountDraw) + getString(
                R.string.m1004_table
            ) + "\n " +
                    getString(R.string.m1004_PlayerWin) + iCountPlayerWin + getString(R.string.m1004_table) + "\n " +
                    getString(R.string.m1004_comWin) + iCountComWin + getString(R.string.m1004_table) + "\n " +
                    getString(R.string.m1004_draw) + iCountDraw + getString(R.string.m1004_table)
        txtResult!!.text = s
    }

    override fun onBackPressed() {}
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> finish()
        }
        return super.onOptionsItemSelected(item)
    } //---------------------------------------------------------------
}