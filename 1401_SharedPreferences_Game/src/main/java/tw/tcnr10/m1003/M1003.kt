package tw.tcnr10.m1003

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import tw.tcnr10.m1003.M1003

class M1003 : AppCompatActivity(), ViewSwitcher.ViewFactory,
    View.OnClickListener {
    private var comp: ImageSwitcher? = null
    private var choice: TextView? = null
    private var result: TextView? = null
    private var scissors: ImageButton? = null
    private var stone: ImageButton? = null
    private var paper: ImageButton? = null
    private var user_select: String? = null
    private var answer: String? = null
    private var startmusic: MediaPlayer? = null
    private var mediaWin: MediaPlayer? = null
    private var mediaLose: MediaPlayer? = null
    private var mediaDraw: MediaPlayer? = null
    private var endMusic: MediaPlayer? = null
    private var toast: Toast? = null
    private var r_layout: RelativeLayout? = null
    private var stats: Button? = null
    private var iwin = 0
    private var ilose = 0
    private var wedraw = 0
    private var endGame: Button? = null
    private var restartGame: Button? = null
    private var myName: TextView? = null
    override fun onClick(v: View) {
        val iComPlay = (Math.random() * 3 + 1).toInt() //1,Scissors 2,Stone 3,Paper
        var bundle = Bundle()
        when (v.id) {
            R.id.m1003_b001 -> {
                user_select = getString(R.string.m1003_t004) + " " + getString(R.string.m1003_b001)
                u_setAlpha()
                scissors!!.background.alpha = 130
                when (iComPlay) {
                    1 -> {
                        comp!!.setImageResource(R.drawable.scissors)
                        answer =
                            getString(R.string.m1003_t005) + " " + getString(R.string.m1003_f003)
                        result!!.setTextColor(getColor(R.color.Yellow))
                        music(3)
                    }
                    2 -> {
                        comp!!.setImageResource(R.drawable.stone)
                        answer =
                            getString(R.string.m1003_t005) + " " + getString(R.string.m1003_f002)
                        result!!.setTextColor(getColor(R.color.Red))
                        music(2)
                    }
                    3 -> {
                        comp!!.setImageResource(R.drawable.net)
                        answer =
                            getString(R.string.m1003_t005) + " " + getString(R.string.m1003_f001)
                        result!!.setTextColor(getColor(R.color.Lime))
                        music(1)
                    }
                }
            }
            R.id.m1003_b002 -> {
                user_select = getString(R.string.m1003_t004) + " " + getString(R.string.m1003_b002)
                u_setAlpha()
                stone!!.background.alpha = 130
                when (iComPlay) {
                    1 -> {
                        comp!!.setImageResource(R.drawable.scissors)
                        answer =
                            getString(R.string.m1003_t005) + " " + getString(R.string.m1003_f001)
                        result!!.setTextColor(getColor(R.color.Lime))
                        music(1)
                    }
                    2 -> {
                        comp!!.setImageResource(R.drawable.stone)
                        answer =
                            getString(R.string.m1003_t005) + " " + getString(R.string.m1003_f003)
                        result!!.setTextColor(getColor(R.color.Yellow))
                        music(3)
                    }
                    3 -> {
                        comp!!.setImageResource(R.drawable.net)
                        answer =
                            getString(R.string.m1003_t005) + " " + getString(R.string.m1003_f002)
                        result!!.setTextColor(getColor(R.color.Red))
                        music(2)
                    }
                }
            }
            R.id.m1003_b003 -> {
                user_select = getString(R.string.m1003_t004) + " " + getString(R.string.m1003_b003)
                u_setAlpha()
                paper!!.background.alpha = 130
                when (iComPlay) {
                    1 -> {
                        comp!!.setImageResource(R.drawable.scissors)
                        answer =
                            getString(R.string.m1003_t005) + " " + getString(R.string.m1003_f002)
                        result!!.setTextColor(getColor(R.color.Red))
                        music(2)
                    }
                    2 -> {
                        comp!!.setImageResource(R.drawable.stone)
                        answer =
                            getString(R.string.m1003_t005) + " " + getString(R.string.m1003_f001)
                        result!!.setTextColor(getColor(R.color.Lime))
                        music(1)
                    }
                    3 -> {
                        comp!!.setImageResource(R.drawable.net)
                        answer =
                            getString(R.string.m1003_t005) + " " + getString(R.string.m1003_f003)
                        result!!.setTextColor(getColor(R.color.Yellow))
                        music(3)
                    }
                }
            }
            R.id.m1003_b004 -> {
                val intent = Intent()
                intent.setClass(this, GameResult::class.java)
                bundle.putInt("KEY_WIN", iwin)
                bundle.putInt("KEY_LOSE", ilose)
                bundle.putInt("KEY_DRAW", wedraw)
                intent.putExtras(bundle)
                u_savedata()
                startActivity(intent)
            }
            R.id.m1003_b005 -> {
                intent = Intent()
                bundle = Bundle()
                intent.setClass(this, GameResult::class.java)
                bundle.putInt("KEY_WIN", iwin)
                bundle.putInt("KEY_LOSE", ilose)
                bundle.putInt("KEY_DRAW", wedraw)
                intent.putExtras(bundle)
                setResult(Activity.RESULT_OK, intent)
                u_savedata()
                finish()
            }
            R.id.m1003_b006 -> {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
        comp!!.clearAnimation()
        val anim =
            AnimationUtils.loadAnimation(this, R.anim.anim_trans_in)
        anim.interpolator = BounceInterpolator()
        comp!!.animation = anim
        choice!!.text = user_select
        result!!.text = answer
    }

    private val cheated =
        View.OnClickListener {
            Toast.makeText(this@M1003, "請稍等", Toast.LENGTH_SHORT).show()
            for (i in 0..1000) {
                paper!!.callOnClick()
                scissors!!.callOnClick()
                stone!!.callOnClick()
            }
            Toast.makeText(this@M1003, "3000局過後", Toast.LENGTH_SHORT).show()
        }

    override fun onBackPressed() {
//        super.onBackPressed();
//        overridePendingTransition(R.anim.anim_scale_rotate_in, R.anim.anim_scale_rotate_out);
//        music(4);
    }

    override fun onStart() {
        u_loaddata()
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.m1003)
        setupViewComponent()
    }

    private fun setupViewComponent() {
        comp = findViewById<View>(R.id.m1003_c001) as ImageSwitcher
        comp!!.setFactory(this)
        //開機動畫
        r_layout = findViewById<View>(R.id.m1003_r001) as RelativeLayout
        r_layout!!.setBackgroundResource(R.drawable.back03)
        r_layout!!.animation = AnimationUtils.loadAnimation(
            this,
            R.anim.anim_scale_rotate_in
        )
        choice = findViewById<View>(R.id.m1003_t004) as TextView
        result = findViewById<View>(R.id.m1003_t005) as TextView
        scissors = findViewById<View>(R.id.m1003_b001) as ImageButton
        stone = findViewById<View>(R.id.m1003_b002) as ImageButton
        paper = findViewById<View>(R.id.m1003_b003) as ImageButton
        stats = findViewById<View>(R.id.m1003_b004) as Button
        endGame = findViewById<View>(R.id.m1003_b005) as Button
        restartGame = findViewById<View>(R.id.m1003_b006) as Button
        myName = findViewById<View>(R.id.myName) as TextView

        //設定選取框框
        u_setAlpha()

        //開啟播放片頭曲
        startmusic = MediaPlayer.create(applicationContext, R.raw.guess)
        startmusic!!.start()

        //設定音樂檔連結
        mediaWin = MediaPlayer.create(applicationContext, R.raw.vctory)
        mediaLose = MediaPlayer.create(applicationContext, R.raw.lose)
        mediaDraw = MediaPlayer.create(applicationContext, R.raw.haha)
        endMusic = MediaPlayer.create(applicationContext, R.raw.byebye)
        scissors!!.setOnClickListener(this)
        stone!!.setOnClickListener(this)
        paper!!.setOnClickListener(this)
        stats!!.setOnClickListener(this)
        restartGame!!.setOnClickListener(this)
        endGame!!.setOnClickListener(this)
        myName!!.setOnClickListener(cheated)
    }

    private fun u_setAlpha() {
        scissors!!.setBackgroundResource(R.drawable.circle_shape)
        scissors!!.background.alpha = 0
        stone!!.setBackgroundResource(R.drawable.circle_shape)
        stone!!.background.alpha = 0
        paper!!.setBackgroundResource(R.drawable.circle_shape)
        paper!!.background.alpha = 0
    }

    private fun music(i: Int) {
        if (startmusic!!.isPlaying) startmusic!!.stop()
        if (mediaDraw!!.isPlaying) mediaDraw!!.pause()
        if (mediaWin!!.isPlaying) mediaWin!!.pause()
        if (mediaLose!!.isPlaying) mediaLose!!.pause()
        when (i) {
            1 -> {
                toast = Toast.makeText(
                    applicationContext,
                    getString(R.string.m1003_f001),
                    Toast.LENGTH_SHORT
                )
//                toast.setGravity(Gravity.BOTTOM, 0, 150)
                //toast.show();
                mediaWin!!.start()
                iwin++
            }
            2 -> {
                toast = Toast.makeText(
                    applicationContext,
                    getString(R.string.m1003_f002),
                    Toast.LENGTH_SHORT
                )
//                toast.setGravity(Gravity.BOTTOM, 0, 150)
                //toast.show();
                mediaLose!!.start()
                ilose++
            }
            3 -> {
                toast = Toast.makeText(
                    applicationContext,
                    getString(R.string.m1003_f003),
                    Toast.LENGTH_SHORT
                )
//                toast.setGravity(Gravity.BOTTOM, 0, 150)
                //toast.show();
                mediaDraw!!.start()
                wedraw++
            }
            4 -> endMusic!!.start()
        }
    }

    override fun makeView(): View {
        val v = ImageView(this)
        v.layoutParams = FrameLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        return v
    }

    private fun u_savedata() {
        val gameResultData = getSharedPreferences("GAME_RESULT", 0)
        gameResultData.edit()
            .putInt("COUNT_WIN", iwin)
            .putInt("COUNT_DRAW", wedraw)
            .putInt("COUNT_LOSE", ilose) //                .putInt("COUNT_SET",iwin+ilose+wedraw)
            .commit()
        Toast.makeText(this@M1003, getString(R.string.m1003_save), Toast.LENGTH_SHORT).show()
    }

    private fun u_loaddata() {
        val gameResultData = getSharedPreferences("GAME_RESULT", 0)
        iwin = gameResultData.getInt("COUNT_WIN", 0)
        wedraw = gameResultData.getInt("COUNT_DRAW", 0)
        ilose = gameResultData.getInt("COUNT_LOSE", 0)
        Toast.makeText(this@M1003, getString(R.string.m1003_load), Toast.LENGTH_SHORT).show()
    }

    private fun u_cleardata() {
        val gameResultData = getSharedPreferences("GAME_RESULT", 0)
        gameResultData.edit()
            .clear()
            .commit()
        iwin = 0
        ilose = 0
        wedraw = 0
        Toast.makeText(this@M1003, getString(R.string.m1003_save), Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.m_1004, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_stats -> stats!!.callOnClick()
            R.id.action_save -> u_savedata()
            R.id.action_load -> u_loaddata()
            R.id.action_clear -> u_cleardata()
            R.id.action_settings -> endGame!!.callOnClick()
        }
        return super.onOptionsItemSelected(item)
    }
}