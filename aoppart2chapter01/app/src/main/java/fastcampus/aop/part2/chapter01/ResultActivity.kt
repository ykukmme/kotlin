package fastcampus.aop.part2.chapter01

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class ResultActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //메인 액티비티에서 값 받아오기
        val height = intent.getIntExtra("height", 0)
        val weight = intent.getIntExtra("weight", 0)

        val bmi = weight / (height / 100.0).pow(2.0)

        //bmi 값에따라 표시할 상태 정리
        val resultText = when{
            bmi >= 35.0 -> "고도 비만"
            bmi >= 30.0 -> "중정도 비만"
            bmi >= 25.0 -> "경도 비만"
            bmi >= 23.0 -> "과체중"
            bmi >= 18.5 -> "정상체중"
            else -> "저체중"
        }
        //xml과 연결
        val resultValueTextView = findViewById<TextView>(R.id.bmiReultTextView)
        val resultStringTextView = findViewById<TextView>(R.id.resultTextView)

        //텍스트로 결과 표시하기
        resultValueTextView.text = bmi.toString()
        resultStringTextView.text = resultText
    }
}