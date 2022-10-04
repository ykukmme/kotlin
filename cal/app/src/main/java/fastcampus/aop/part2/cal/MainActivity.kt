package fastcampus.aop.part2.cal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val expressionTextView: TextView by lazy {
        findViewById<TextView>(R.id.expressionTextView)
    }

    private val resultTextView: TextView by lazy {
        findViewById<TextView>(R.id.resultTextView)
    }

    private var isOperator = false
    private var hasOperator = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun ButtonClicked(v: View) {
        when(v.id){
            R.id.Button0 -> numberButtonClicked("0")
            R.id.Button1 -> numberButtonClicked("1")
            R.id.Button2 -> numberButtonClicked("2")
            R.id.Button3 -> numberButtonClicked("3")
            R.id.Button4 -> numberButtonClicked("4")
            R.id.Button5 -> numberButtonClicked("5")
            R.id.Button6 -> numberButtonClicked("6")
            R.id.Button7 -> numberButtonClicked("7")
            R.id.Button8 -> numberButtonClicked("8")
            R.id.Button9 -> numberButtonClicked("9")
            R.id.Button_plus -> operatorButtonClicked("+")
            R.id.Button_minus -> operatorButtonClicked("-")
            R.id.Button_multi -> operatorButtonClicked("*")
            R.id.Button_divider -> operatorButtonClicked("/")
            R.id.Button_modulo -> operatorButtonClicked("%")
        }
    }

    private fun numberButtonClicked(number: String){

        if (isOperator) {
            expressionTextView.append(" ")
        }

        isOperator = false

        val expressionText = expressionTextView.text.split(" ")

        if (expressionText.isNotEmpty() && expressionText.last().length >= 15) {
            Toast.makeText(this, "15자리 까지만 사용할 수 있습니다", Toast.LENGTH_SHORT).show()
            return
        } else if (expressionText.last().isEmpty() && number == "0") {
            Toast.makeText(this, "0은 제일 앞에 올 수 없습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        expressionTextView.append(number)

        //TODO resultTextView에 실시간으로 계산 결과를  넣어야 하는 기능
    }

    private fun operatorButtonClicked(operator: String) {

        if (expressionTextView.text.isEmpty()) {
            return
        }

        when {
            isOperator -> {
                val text = expressionTextView.text.toString()
                expressionTextView.text = text.dropLast(1) + operator
            }

            hasOperator -> {
                Toast.makeText(this, "연산자는 한 번만 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
                return
            }
            else -> {
                expressionTextView.append(" $operator")
            }
        }

        //계산기 화면에 표시되는 문자들 중에 연산자만 초록색으로 보이게 하는 코드
        val ssb = SpannableStringBuilder(expressionTextView.text)
        ssb.setSpan(
            ForegroundColorSpan(getColor(R.color.green)), expressionTextView.text.length -1,
                expressionTextView.text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        expressionTextView.text = ssb

        isOperator = true
        hasOperator = true
    }

    fun resultButtonClicked(v: View) {

    }

    fun historyButtonClicked(v: View) {

    }

    fun clearButtonClicked(v: View) {

    }
}