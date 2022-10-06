package fastcampus.aop.part2.timer

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val remainMinutesTextView: TextView by lazy {
        findViewById(R.id.remainMinutesTextView)
    }

    private val remainSecondsTextView: TextView by lazy {
        findViewById(R.id.remainSecondsTextView)
    }

    private val seekBar: SeekBar by lazy {
        findViewById(R.id.seekBar)
    }

    private var currentCpuntDownTimer: CountDownTimer? = null

    private val soundPool = SoundPool.Builder().build()
    private var tickingSoundId: Int? = null
    private var bellSoundId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
        initSounds()
    }

    override fun onResume() {
        super.onResume()
        soundPool.autoResume()
    }

    override fun onPause() {
        super.onPause()
        soundPool.autoPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }

    private fun bindViews() {
        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        updateRemainTime(progress * 60 * 1000L)
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                    stopCountDown()
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {

                    if (seekBar.progress == 0) {
                        stopCountDown()
                    } else {
                        startCountDown()
                    }

                }
            }
        )
    }

    private fun initSounds() {
        tickingSoundId = soundPool.load(this, R.raw.timer_ticking, 1)
        bellSoundId = soundPool.load(this, R.raw.timer_bell, 1)
    }

    private fun createCountDownTimer(initialMillis: Long): CountDownTimer {
        return object : CountDownTimer(initialMillis, 1000L) {
            override fun onTick(p0: Long) {

                updateRemainTime(p0)
                updateSeekBar(p0)
            }

            override fun onFinish() {

                completeCountDown()

            }
        }
    }

    private fun startCountDown() {
        currentCpuntDownTimer = createCountDownTimer(seekBar.progress * 60 *1000L)
        currentCpuntDownTimer?.start()

        tickingSoundId?.let {
                soundId -> soundPool.play(soundId, 1F, 1F, 0, -1, 1F)
        }
    }

    private fun completeCountDown() {
        updateRemainTime(0)
        updateSeekBar(0)

        soundPool.autoPause()
        bellSoundId?.let {
                soundId -> soundPool.play(soundId, 1F, 1F, 0, 0, 1F)
        }

    }

    private fun stopCountDown() {
        currentCpuntDownTimer?.cancel()
        currentCpuntDownTimer = null
        soundPool.autoPause()
    }

    private fun updateRemainTime(remainMillis: Long) {

        val remainSeconds = remainMillis / 1000

        remainMinutesTextView.text = "%02d'".format(remainSeconds / 60)
        remainSecondsTextView.text = "%02d".format(remainSeconds % 60)
    }

    private fun updateSeekBar(remainMillis: Long) {

        seekBar.progress = (remainMillis / 1000 / 60).toInt()
    }

}