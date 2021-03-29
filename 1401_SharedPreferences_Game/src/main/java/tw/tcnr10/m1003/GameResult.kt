package tw.tcnr10.m1003

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameResult : AppCompatActivity(), View.OnClickListener {
    private var backBtn: Button? = null
    private var winStats: TextView? = null
    private var loseStats: TextView? = null
    private var drawStats: TextView? = null
    private var totalStats: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_result)
        setupViewComponent()
        ShowResult()
    }

    private fun setupViewComponent() {
        backBtn = findViewById<View>(R.id.btnBackToGame) as Button
        totalStats = findViewById<View>(R.id.edtCountSet) as TextView
        winStats = findViewById<View>(R.id.edtCountPlayerWin) as TextView
        loseStats = findViewById<View>(R.id.edtCountComWin) as TextView
        drawStats = findViewById<View>(R.id.edtCountDraw) as TextView
        backBtn!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
//        Intent intent = new Intent();
//        intent.setClass(this,M1003.class);
//        startActivity(intent);
        finish()
    }

    private fun ShowResult() {
        val bundle = this.intent.extras
        val winCount = bundle!!.getInt("KEY_WIN")
        val loseCount = bundle.getInt("KEY_LOSE")
        val drawCount = bundle.getInt("KEY_DRAW")
        val total = winCount + loseCount + drawCount
        winStats!!.text = Integer.toString(winCount)
        loseStats!!.text = Integer.toString(loseCount)
        drawStats!!.text = Integer.toString(drawCount)
        totalStats!!.text = Integer.toString(total)
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